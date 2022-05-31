package com.yike.apis.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yike.apis.dao.SearchHeatTagDao;
import com.yike.apis.dao.SearchTagDao;
import com.yike.apis.dao.SearchheatDao;
import com.yike.apis.pojo.mini.vo.SearchVo;
import com.yike.apis.pojo.search.SearchHeat;
import com.yike.apis.pojo.search.SearchHeatTag;
import com.yike.apis.pojo.search.SearchTag;
import com.yike.apis.service.SearchService;
import com.yike.apis.utils.RedisUtil;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SearchServiceImpl implements SearchService {
    private final static String search_rank_key = "search_rank_key";
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SearchheatDao searchheatDao;
    @Autowired
    private SearchHeatTagDao searchHeatTagDao;
    @Autowired
    private SearchTagDao searchTagDao;
    @Override
    public ResponseData setSearchHeat(SearchVo searchVo) {
        SearchVo searchVo1 = new SearchVo();
        if(ObjectUtil.isEmpty(searchVo) || ObjectUtil.isEmpty(searchVo.getSearchName())){
            return ResponseDataUtil.buildError("searchName isn't empty");
        }
        Double b = redisUtil.score(search_rank_key,searchVo.getSearchName());
        if(ObjectUtil.isNotEmpty(b)){
            searchVo1 = incrRank(search_rank_key,searchVo.getSearchName(),1D);
        }else {
            QueryWrapper<SearchHeat> wrapper = new QueryWrapper<>();
            wrapper.eq("searchName",searchVo.getSearchName());
            SearchHeat searchheat = searchheatDao.selectOne(wrapper);
            if(ObjectUtil.isNotEmpty(searchheat)){
                searchheat = new SearchHeat();
                searchheat.setShId(IdUtil.simpleUUID());
                searchheat.setHeat(1L);
                searchheat.setStartTime(System.currentTimeMillis());
                searchheat.setLastTime(0L);
                searchheat.setSearchName(searchVo.getSearchName());
                searchheatDao.insert(searchheat);
            }
            redisUtil.add(search_rank_key,searchVo.getSearchName(),1D);
            searchVo1.setSearchName(searchVo.getSearchName());
            searchVo1.setRank(-1L);
            searchVo1.setHeat(1L);
        }

        return ResponseDataUtil.buildSuccess(searchVo1);
    }

    @Override
    public ResponseData getSearchHeat(Integer limit) {
        Set<String> set =  getTopNRanks(search_rank_key,limit);
        List list = new ArrayList();
        if(ObjectUtil.isNotEmpty(set)){
            Long i = 1L;
            for(String s:set){
                Double score = redisUtil.score(search_rank_key, s);
                SearchVo searchVo = new SearchVo();
                searchVo.setSearchName(s);
                searchVo.setHeat(score.longValue());
                searchVo.setRank(i);
                list.add(searchVo);
                i++;
            }
        }
        return ResponseDataUtil.buildSuccess(list);
    }

    @Override
    public void clearCacheSynchronizeData() {
        Set<String> set = redisUtil.revRange(search_rank_key,0,-1);
        if(ObjectUtil.isNotEmpty(set)){
            for(String searchName:set){
                QueryWrapper<SearchHeat> wrapper = new QueryWrapper<>();
                wrapper.eq("searchName",searchName);
                SearchHeat searchheat = searchheatDao.selectOne(wrapper);
                if(ObjectUtil.isNotEmpty(searchheat)){
                    Double score = redisUtil.score(search_rank_key, searchName);
                    searchheat.setLastTime(System.currentTimeMillis());
                    searchheat.setHeat(searchheat.getHeat()+score.longValue());
                    searchheatDao.updateById(searchheat);
                }else {
                    Double score = redisUtil.score(search_rank_key, searchName);
                    searchheat = new SearchHeat();
                    searchheat.setShId(IdUtil.simpleUUID());
                    searchheat.setHeat(ObjectUtil.isEmpty(score) ? 0L : score.longValue());
                    searchheat.setStartTime(System.currentTimeMillis());
                    searchheat.setLastTime(System.currentTimeMillis());
                    searchheat.setSearchName(searchName);
                    searchheatDao.insert(searchheat);

                }
                redisUtil.remove(search_rank_key,searchName);
            }

        }
    }

    @Override
    public ResponseData getTag(String address) {
        List<SearchTag> tagList = new ArrayList<>();
        if(ObjectUtil.isNotEmpty(address)){
            tagList = searchTagDao.selectList(null);
        }else {
            tagList = searchTagDao.selectByAddress(address);
        }
        return ResponseDataUtil.buildSuccess(tagList);
    }

    @Override
    public ResponseData setTag_address(String address, List<SearchTag> tagList) {
        if(ObjectUtil.isEmpty(tagList)){
            return ResponseDataUtil.buildError("TagList isn't empty");
        }
        QueryWrapper<SearchHeat> wrapper = new QueryWrapper<>();
        wrapper.eq("searchName",address);
        SearchHeat searchheat = searchheatDao.selectOne(wrapper);
        if(ObjectUtil.isNotEmpty(searchheat)){
            for(SearchTag tag:tagList){
                SearchHeatTag searchHeatTag = new SearchHeatTag();
                searchHeatTag.setAddress(address);
                searchHeatTag.setShId(searchheat.getShId());
                searchHeatTag.setStId(tag.getStId());
                searchHeatTag.setShtId(IdUtil.simpleUUID());
                searchHeatTagDao.insert(searchHeatTag);
            }
        }
        return ResponseDataUtil.buildSuccess();
    }

    /**
     * Get the top N leaderboard data
     * @param key
     * @param n
     * @return
     */
    public Set<String> getTopNRanks(String key , int n) {
        Set<String> result = redisUtil.revRange(key, 0, n - 1);
        return result;
    }

    /**
     * Get a leaderboard position
     * @param key
     * @param searchName
     * @return
     */
    public SearchVo getRank(String key, String searchName) {
        // Gets the ranking, because the default is 0, so the actual ranking needs +1
        Long rank = redisUtil.rank(key, searchName);
        if (rank == null) {
            // If there is no ranking, a default is returned
            return new SearchVo(-1L, 0L, searchName);
        }

        // For points
        Double score = redisUtil.score(key, String.valueOf(searchName));
        return new SearchVo(rank + 1, score.longValue(), searchName);
    }

    /**
     * Points on the
     * @param key
     * @param searchName
     * @param score
     * @return
     */
    public SearchVo incrRank(String key, String searchName, Double score) {
        redisUtil.incrScore(key, searchName, score);
        Long rank = redisUtil.rank(key, searchName);
        Double score1 = redisUtil.score(key, searchName);
        return new SearchVo(rank + 1, score1.longValue(), searchName);
    }
}
