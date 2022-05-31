package com.yike.apis.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yike.apis.dao.UserDao;
import com.yike.apis.dao.game.*;
import com.yike.apis.pojo.game.*;
import com.yike.apis.pojo.game.GameiconGameprojectUser;
import com.yike.apis.pojo.game.vo.*;
import com.yike.apis.pojo.user.User;
import com.yike.apis.service.GameService;
import com.yike.apis.utils.Coinmarketcap.vo.defi.CryptoCurrencyList;
import com.yike.apis.utils.Coinmarketcap.vo.nft.Collections;
import com.yike.apis.utils.CommentUtils;
import com.yike.apis.utils.RedisUtil;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import com.yike.apis.dao.game.GameprojectlinkedDao;
import com.yike.apis.utils.tokenView.TokenUtil;
import com.yike.apis.utils.tokenView.vo.Maincoinex.Maincoinex;
import com.yike.apis.utils.tokenView.vo.tokenTokentrans.TokenTokentrans;
import com.yike.apis.utils.tokenView.vo.normal.Normal;
import com.yike.apis.utils.tokenView.vo.tokenEth.TokenEth;
import com.yike.apis.utils.tokenView.vo.tokenTokentrans.Data;
import com.yike.apis.utils.tokenView.vo.txeth.TxEth;
import com.yike.apis.utils.twtter.TwtterApi;
import com.yike.apis.utils.twtter.TwtterUtil;
import com.yike.apis.utils.twtter.vo.searchAdaptive.SearchAdaptive;
import com.yike.apis.utils.twtter.vo.searchAdaptive.Tweet;
import com.yike.apis.utils.twtter.vo.searchAdaptive.Tweets;
import com.yike.apis.utils.twtter.vo.searchAdaptive.Users;
import com.yike.apis.utils.twtter.vo.userByScreenName.UserByScreenName;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final static String comment_liked_key = "comment_liked_key";
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedissonClient redisson;
    @Autowired
    private GamefileDao gamefileDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GameiconDao gameiconDao;
    @Autowired
    private GameprojectDao gameprojectDao;
    @Autowired
    private GameremarkDao gameremarkDao;
    @Autowired
    private GameiconGameprojectUserDao gameiconGameprojectUserDao;
    @Autowired
    private GameprojectGamefileDao gameprojectGamefileDao;
    @Autowired
    private GameprojectlinkedDao gameprojectlinkedDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public ResponseData getGameItems(String projectName,String sortField,String sort,Integer page, Integer pageSize) {
        if(ObjectUtil.isEmpty(page)){
            page = 1;
        }
        if(ObjectUtil.isEmpty(pageSize)){
            pageSize = 20;
        }
        QueryWrapper<GameprojectVo> wrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(projectName)){
            wrapper.like("name",projectName);
        }
        if(ObjectUtil.isNotEmpty(sortField)){
            if(ObjectUtil.isNotEmpty(sort) && sort.equals("asc")){
                wrapper.orderByAsc(sortField);
            }else {
                wrapper.orderByDesc(sortField);
            }
        }
        Page<GameprojectVo> iPage = new Page<GameprojectVo>(page, pageSize);
        Page<GameprojectVo> gameprojectVos = gameprojectDao.getGameItems(iPage,wrapper);
        return ResponseDataUtil.buildSuccess(gameprojectVos);
    }

    @Override
    public ResponseData getComments(String gpId, String uId,String sort,Integer page, Integer pageSize) {
        if(ObjectUtil.isEmpty(page)){
            page = 1;
        }
        if(ObjectUtil.isEmpty(pageSize)){
            pageSize = 20;
        }
        QueryWrapper<GameremarkVo> wrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(gpId)){
            wrapper.eq("gpId",gpId);
        }
        wrapper.eq("parentId","0");
        if(ObjectUtil.isNotEmpty(sort) && sort.equals("asc")){
            wrapper.orderByAsc("time");
        }else {
            wrapper.orderByDesc("time");
        }
        Page<GameremarkVo> iPage = new Page<GameremarkVo>(page, pageSize);
        Page<GameremarkVo> gameremarkVoPage = gameremarkDao.getComments(iPage,wrapper);
        List<GameremarkVo> gameremarkVos = gameremarkVoPage.getRecords();
        List<GameremarkVo> child = new ArrayList<>();
        if(ObjectUtil.isNotEmpty(gameremarkVos) && ObjectUtil.isNotEmpty(uId)){
            child.addAll(gameremarkVos);
            for(GameremarkVo gameremarkVo:gameremarkVos){
                Boolean bo = redisTemplate.opsForHash().hasKey(gameremarkVo.getGrId(),uId);
                gameremarkVo.setIsLiked(bo);
                if(ObjectUtil.isNotEmpty(gameremarkVo.getRootParentId()) && !gameremarkVo.getRootParentId().equals("0")){
                    List<GameremarkVo> c = gameremarkDao.getCommentsByRootParentId(gameremarkVo.getRootParentId());
                    child.addAll(c);
                }
            }
        }
        child = CommentUtils.processGameremarkVos(child,true);
        gameremarkVoPage.setRecords(child);
        return ResponseDataUtil.buildSuccess(gameremarkVoPage);
    }

    @Override
    public ResponseData comment(Gameremark gameremark) {
        if(ObjectUtil.isEmpty(gameremark)){
            return ResponseDataUtil.buildError("The value anomaly");
        }
        if(ObjectUtil.isEmpty(gameremark.getContent())){
            return ResponseDataUtil.buildError("The value anomaly");
        }
        if(ObjectUtil.isEmpty(gameremark.getGpId())){
            return ResponseDataUtil.buildError("The value anomaly");
        }
        if(ObjectUtil.isEmpty(gameremark.getUId())){
            return ResponseDataUtil.buildError("The value anomaly");
        }
        User user = userDao.selectById(gameremark.getUId());
        if(ObjectUtil.isEmpty(user)){
            return ResponseDataUtil.buildError("The value anomaly");
        }
        if(ObjectUtil.isNotEmpty(gameremark.getParentId())){
            Gameremark gameremark1 = gameremarkDao.selectById(gameremark.getParentId());
            if(ObjectUtil.isEmpty(gameremark1)){
                return ResponseDataUtil.buildError("The value anomaly");
            }
        }else {
            gameremark.setParentId("0");
        }
        if(ObjectUtil.isNotEmpty(gameremark.getRootParentId())){
            Gameremark gameremark1 = gameremarkDao.selectById(gameremark.getRootParentId());
            if(ObjectUtil.isEmpty(gameremark1)){
                return ResponseDataUtil.buildError("The value anomaly");
            }
        }else {
            gameremark.setRootParentId("0");
        }
        gameremark.setGrId(IdUtil.simpleUUID());
        gameremark.setTime(System.currentTimeMillis());
        int i = gameremarkDao.insert(gameremark);
        if(i < 1){
            return ResponseDataUtil.buildError();
        }
        return ResponseDataUtil.buildSuccess();
    }

    @Override
    public ResponseData getGameIcon() {
        List<Gameicon> list = gameiconDao.selectList(null);
        return ResponseDataUtil.buildSuccess(list);
    }

    @Override
    public ResponseData getGameIconByGpId(String gpId, String uId,Integer page, Integer pageSize) {
        if(ObjectUtil.isEmpty(page)){
            page = 1;
        }
        if(ObjectUtil.isEmpty(pageSize)){
            pageSize = 20;
        }
        QueryWrapper<GameiconVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.gpId",gpId);
        Page<GameiconVo> iPage = new Page<GameiconVo>(page, pageSize);
        Page<GameiconVo> gameiconVoPage = gameiconGameprojectUserDao.getGameIconByGpId(iPage,wrapper);
        List<GameiconVo> list1 = gameiconDao.selectListGameiconVo();
        List<GameiconVo> list = new ArrayList<>();
        if(ObjectUtil.isNotEmpty(gameiconVoPage.getRecords())){
            for(GameiconVo gameiconVo:gameiconVoPage.getRecords()){
                if(ObjectUtil.isNotEmpty(gameiconVo.getUserIcons()) && ObjectUtil.isNotEmpty(uId)){
                    List<com.yike.apis.pojo.game.vo.UserVo> userIcons = gameiconVo.getUserIcons();
                    boolean b = userIcons.stream().anyMatch(s -> s.getUId().equals(uId));
                    gameiconVo.setIsLiked(b);
                }
            }
            list.addAll(gameiconVoPage.getRecords());
        }
        if(ObjectUtil.isNotEmpty(list1)){
            list.addAll(list1);
        }
        ArrayList<GameiconVo> mergeList3 = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(GameiconVo::getGiId))), ArrayList::new));
        gameiconVoPage.setRecords(mergeList3);
        return ResponseDataUtil.buildSuccess(gameiconVoPage);
    }

    @Override
    @Transactional
    public ResponseData save(Gameprojectlinked gameprojectlinked) {
        Gameprojectlinked gameprojectlinked1 = gameprojectlinkedDao.selectById(gameprojectlinked.getGplId());
        int i = 0;
        if(ObjectUtil.isNotEmpty(gameprojectlinked1)){
            i = gameprojectlinkedDao.updateById(gameprojectlinked);
        }else {
            gameprojectlinked.setGplId(IdUtil.simpleUUID());
            i = gameprojectlinkedDao.insert(gameprojectlinked);
        }
        if(i < 1){
            return ResponseDataUtil.buildError();
        }
        return ResponseDataUtil.buildSuccess();
    }


    @Override
    public ResponseData getLikedListByLikedByLikedGpId(String likedGpId,Integer status,Integer page, Integer pageSize) {
        if(ObjectUtil.isEmpty(page)){
            page = 1;
        }
        if(ObjectUtil.isEmpty(pageSize)){
            pageSize = 20;
        }
        QueryWrapper<Gameprojectlinked> wrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(likedGpId)){
            wrapper.eq("linkedGameprojectId",likedGpId);
        }
        if(ObjectUtil.isNotEmpty(status)){
            wrapper.eq("status",status);
        }
        Page<Gameprojectlinked> iPage = new Page<Gameprojectlinked>(page, pageSize);
        Page<Gameprojectlinked> gameprojectlinkedPage = gameprojectlinkedDao.selectPage(iPage,wrapper);
        return ResponseDataUtil.buildSuccess(gameprojectlinkedPage);
    }

    @Override
    public ResponseData getLikedListBylikedUserId(String likedUserId,Integer status,Integer page, Integer pageSize) {
        if(ObjectUtil.isEmpty(page)){
            page = 1;
        }
        if(ObjectUtil.isEmpty(pageSize)){
            pageSize = 20;
        }
        QueryWrapper<Gameprojectlinked> wrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(likedUserId)){
            wrapper.eq("linkedUserId",likedUserId);
        }
        if(ObjectUtil.isNotEmpty(status)){
            wrapper.eq("status",status);
        }
        Page<Gameprojectlinked> iPage = new Page<Gameprojectlinked>(page, pageSize);
        Page<Gameprojectlinked> gameprojectlinkedPage = gameprojectlinkedDao.selectPage(iPage,wrapper);
        return ResponseDataUtil.buildSuccess(gameprojectlinkedPage);
    }

    @Override
    public ResponseData getByLikedUserIdAndLikedGpId(String likedGpId, String likedUserId) {
        Gameprojectlinked gameprojectlinked = getByLikedUserIdAndLikedGpId2(likedGpId,likedUserId);
        return ResponseDataUtil.buildSuccess(gameprojectlinked);
    }

    public Gameprojectlinked getByLikedUserIdAndLikedGpId2(String likedGpId, String likedUserId) {
        QueryWrapper<Gameprojectlinked> wrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(likedGpId)){
            wrapper.eq("linkedGameprojectId",likedGpId);
        }
        if(ObjectUtil.isNotEmpty(likedUserId)){
            wrapper.eq("linkedUserId",likedUserId);
        }
        Gameprojectlinked gameprojectlinked = gameprojectlinkedDao.selectOne(wrapper);
        return gameprojectlinked;
    }

    @Override
    @Transactional
    public void transLikedFromRedis2DB() {
        List<Gameprojectlinked> list = redisUtil.getLikedDataFromRedis();
        for (Gameprojectlinked like : list) {
            Gameprojectlinked ul = getByLikedUserIdAndLikedGpId2(like.getLinkedGameprojectId(), like.getLinkedUserId());
            if (ul == null){
                //没有记录，直接存入
                save(like);
            }else{
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                save(ul);
            }
        }
    }

    @Override
    @Transactional
    public void transLikedCountFromRedis2DB() {
        List<LikedCountVo> list = redisUtil.getLikedCountFromRedis();
        for (LikedCountVo dto : list) {
            Gameproject gameproject = gameprojectDao.selectById(dto.getLinkedGameprojectId());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (gameproject != null){
                Long likeNum = gameproject.getLiked() + dto.getCount();
                gameproject.setLiked(likeNum);
                gameproject.setMoon(likeNum);
                //更新点赞数量
                gameprojectDao.updateById(gameproject);
            }
        }

        List<LikedCountVo> list1 = redisUtil.getRoorCountFromRedis();
        for (LikedCountVo dto : list1) {
            Gameproject gameproject = gameprojectDao.selectById(dto.getLinkedGameprojectId());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (gameproject != null){
                Long roorNum = gameproject.getLiked() + dto.getCount();
                gameproject.setNgmi(roorNum);
                //更新点赞数量
                gameprojectDao.updateById(gameproject);
            }
        }
    }

    @Override
    public ResponseData liked(LikedVo likedVo) {
        if(likedVo.getStatus() == 1){
            redisUtil.saveLiked2Redis(likedVo.getLikedGpId(), likedVo.getLikedUserId());
            redisUtil.incrementLikedCount(likedVo.getLikedGpId());
        }else if(likedVo.getStatus() == 0){
            redisUtil.unlikeFromRedis(likedVo.getLikedGpId(), likedVo.getLikedUserId());
            redisUtil.decrementLikedCount(likedVo.getLikedGpId());
        }else if(likedVo.getStatus() == -1){
            redisUtil.savePoorRedis(likedVo.getLikedGpId(), likedVo.getLikedUserId());
            redisUtil.incrementPoorCount(likedVo.getLikedGpId());
        }else if(likedVo.getStatus() == -2){
            redisUtil.unpoorFromRedis(likedVo.getLikedGpId(), likedVo.getLikedUserId());
            redisUtil.decrementPoorCount(likedVo.getLikedGpId());
        }else {
            return ResponseDataUtil.buildError("Status Abnormal value transmission");
        }
        return ResponseDataUtil.buildSuccess();
    }

    @Override
    public ResponseData socialMedia() {
        SearchAdaptive searchAdaptive = (SearchAdaptive) redisTemplate.opsForValue().get("gamefi");
        List<Tweet> tweetList = new ArrayList<>();
        if(ObjectUtil.isNotEmpty(searchAdaptive)){
            List<Tweets> tweets = searchAdaptive.getTweets();
            List<Users> users = searchAdaptive.getUsers();
            for(int i = 0;i < tweets.size();i++){
                Tweet tweet = new Tweet();
                if(ObjectUtil.isNotEmpty(tweets.get(i))){
                    tweet.setFullText(tweets.get(i).getFullText());
                    tweet.setCreatedAt(tweets.get(i).getCreatedAt());
                    tweet.setId(tweets.get(i).getIdStr());
                    tweet.setUserId(tweets.get(i).getUserIdStr());
                    if(ObjectUtil.isNotEmpty(tweets.get(i).getExtendedEntities())
                    && ObjectUtil.isNotEmpty(tweets.get(i).getExtendedEntities().getMedia())){
                        tweet.setMediaUrl(tweets.get(i).getExtendedEntities().getMedia().get(0).getMediaUrl());
                        tweet.setUrl(tweets.get(i).getExtendedEntities().getMedia().get(0).getUrl());
                    }
                }
                if(ObjectUtil.isNotEmpty(users.get(i))){
                    tweet.setName(users.get(i).getName());
                    tweet.setScreenName(users.get(i).getScreenName());
                    tweet.setProfileImageUrlHttps(users.get(i).getProfileImageUrlHttps());
                }
                tweetList.add(tweet);
            }
        }
        return ResponseDataUtil.buildSuccess(tweetList);
    }

    @Override
    public void GameThreePartyDataSynchronization() {
        Map map = TwtterUtil.searchAdaptive(TwtterApi.searchAdaptive.getUrl(), TwtterApi.searchAdaptive.getAuthorization(), TwtterApi.searchAdaptive.getCookie(), TwtterApi.searchAdaptive.getxCsrfToken(),"gamefi","30");
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        List list = new ArrayList();
        List list1 = new ArrayList();
        map = (Map) map.get("globalObjects");
        map1 = (Map) map.get("tweets");
        map2 = (Map) map.get("users");
        if(ObjectUtil.isNotEmpty(map1) && map1.keySet().size() > 0){
            for(Object o:map1.keySet()){
                Map s = (Map) map1.get(o);
                list.add(map1.get(o));
                list1.add(map2.get(s.get("user_id_str")));
            }
        }
        map3.put("tweets",list);
        map3.put("users",list1);
        String s = JSONObject.toJSONString(map3);
        SearchAdaptive searchAdaptive = JSONUtil.toBean(s,SearchAdaptive.class);
        redisTemplate.opsForValue().set("gamefi",searchAdaptive);
        List<Gameproject> gameprojectList = gameprojectDao.selectList(null);
        if(ObjectUtil.isNotEmpty(gameprojectList)){
            for(Gameproject gameproject:gameprojectList){
                if(ObjectUtil.isNotEmpty(gameproject.getTwtter())){
                    UserByScreenName userByScreenName = TwtterUtil.userByScreenName(TwtterApi.userByScreenName.getUrl(), TwtterApi.userByScreenName.getAuthorization(), TwtterApi.userByScreenName.getCookie(), TwtterApi.userByScreenName.getxCsrfToken(),gameproject.getTwtter());
                    if(ObjectUtil.isNotEmpty(userByScreenName) && ObjectUtil.isNotEmpty(userByScreenName.getData())
                            && ObjectUtil.isNotEmpty(userByScreenName.getData().getUser()) && ObjectUtil.isNotEmpty(userByScreenName.getData().getUser().getResult())
                            && ObjectUtil.isNotEmpty(userByScreenName.getData().getUser().getResult().getLegacy())){
                        gameproject.setTwitterFollower(userByScreenName.getData().getUser().getResult().getLegacy().getNormalFollowersCount());
                    }
                }

                if(ObjectUtil.isNotEmpty(gameproject.getTokenHash())){
                    TokenEth tokenEth = TokenUtil.tokenEth(gameproject.getTokenHash());
                    if(ObjectUtil.isNotEmpty(tokenEth) && ObjectUtil.isNotEmpty(tokenEth.getData()) && ObjectUtil.isNotEmpty(tokenEth.getData().getHolderCnt())){
                        gameproject.setActiveUsers(tokenEth.getData().getHolderCnt());
                    }
                }

                if(gameproject.getSpecies().equals("defi")){
                    Object o = redisTemplate.opsForValue().get(gameproject.getName().toLowerCase());
                    if(ObjectUtil.isNotEmpty(o)){
                        CryptoCurrencyList cryptoCurrencyList = (CryptoCurrencyList) o;
                        gameproject.setVolume(cryptoCurrencyList.getQuotes().get(2).getVolume24h().toString());
                        gameproject.setTotalNFT(cryptoCurrencyList.getTotalSupply().toString());
                        gameproject.setCirculatingSupply(cryptoCurrencyList.getCirculatingSupply().toString());
                    }
                    Object o1 = redisTemplate.opsForValue().get(gameproject.getName().toLowerCase()+"price");
                    BigDecimal price = BigDecimal.valueOf(0);
                    if(ObjectUtil.isNotEmpty(o1)){
                        price = new BigDecimal(o1.toString());
                    }
                    gameproject.setPrice(price.toPlainString());
                }else if(gameproject.getSpecies().equals("nft")){
                    Object o = redisTemplate.opsForValue().get(gameproject.getName().toLowerCase());
                    if(ObjectUtil.isNotEmpty(o)){
                        Collections collections = (Collections) o;
                        gameproject.setVolume(collections.getVolume7d().toString());
                        gameproject.setTotalNFT(collections.getSalesAT().toString());
                        gameproject.setCirculatingSupply(collections.getSalesAT().toString());
                    }
                    Object o1 = redisTemplate.opsForValue().get(gameproject.getName().toLowerCase()+"price");
                    BigDecimal price = BigDecimal.valueOf(0);
                    if(ObjectUtil.isNotEmpty(o1)){
                        price = new BigDecimal(o1.toString());
                    }
                    gameproject.setPrice(price.toPlainString());
                }
//                if(ObjectUtil.isNotEmpty(gameproject.getOpenseaName())){
//                    Collection collection = OpenseaUtil.collection(OpenseaApi.collection.getUrl(),OpenseaApi.collection.getX_API_KEY(),OpenseaApi.collection.getAccept(),gameproject.getOpenseaName());
//                    if(ObjectUtil.isNotEmpty(collection) && ObjectUtil.isNotEmpty(collection.getStats())){
//                        gameproject.setVolume(collection.getStats().getOneDayVolume().toString());
//                        gameproject.setTotalNFT(collection.getStats().getTotalVolume().longValue());
//                    }
//                }
                gameprojectDao.updateById(gameproject);
            }
        }
    }

    @Override
    public ResponseData userSetGameIcon(GameiconGameprojectUser gameiconGameprojectUser) {
        redisUtil.lock(redisson,gameiconGameprojectUser.getGpId()+gameiconGameprojectUser.getGiId()+gameiconGameprojectUser.getUId());
        if(ObjectUtil.isEmpty(gameiconGameprojectUser.getUId()) || ObjectUtil.isEmpty(gameiconGameprojectUser.getGpId()) || ObjectUtil.isEmpty(gameiconGameprojectUser.getGiId())){
            redisUtil.unlock(redisson,gameiconGameprojectUser.getGpId()+gameiconGameprojectUser.getGiId()+gameiconGameprojectUser.getUId());
            return ResponseDataUtil.buildError("The value anomaly");
        }else {
            User user = userDao.selectById(gameiconGameprojectUser.getUId());
            Gameproject gameproject = gameprojectDao.selectById(gameiconGameprojectUser.getGpId());
            Gameicon gameicon = gameiconDao.selectById(gameiconGameprojectUser.getGiId());
            if(ObjectUtil.isEmpty(user) || ObjectUtil.isEmpty(gameproject) || ObjectUtil.isEmpty(gameicon)){
                redisUtil.unlock(redisson,gameiconGameprojectUser.getGpId()+gameiconGameprojectUser.getGiId()+gameiconGameprojectUser.getUId());
                return ResponseDataUtil.buildError("The value anomaly");
            }
        }
        QueryWrapper<GameiconGameprojectUser> wrapper = new QueryWrapper<>();
        wrapper.eq("giId",gameiconGameprojectUser.getGiId());
        wrapper.eq("uId",gameiconGameprojectUser.getUId());
        wrapper.eq("gpId",gameiconGameprojectUser.getGpId());
        GameiconGameprojectUser gameiconGameprojectUser1 = gameiconGameprojectUserDao.selectOne(wrapper);
        if(ObjectUtil.isNotEmpty(gameiconGameprojectUser1)) {
            redisUtil.unlock(redisson,gameiconGameprojectUser.getGpId()+gameiconGameprojectUser.getGiId()+gameiconGameprojectUser.getUId());
            return ResponseDataUtil.buildError("Please do not like the icon repeatedly");
        }
        gameiconGameprojectUser.setGiuId(IdUtil.simpleUUID());
        int i = gameiconGameprojectUserDao.insert(gameiconGameprojectUser);
        if(i < 1){
            redisUtil.unlock(redisson,gameiconGameprojectUser.getGpId()+gameiconGameprojectUser.getGiId()+gameiconGameprojectUser.getUId());
            return ResponseDataUtil.buildError();
        }
        redisUtil.unlock(redisson,gameiconGameprojectUser.getGpId()+gameiconGameprojectUser.getGiId()+gameiconGameprojectUser.getUId());
        return ResponseDataUtil.buildSuccess();
    }

    @Override
    public ResponseData getBuyTokenImg(String gpId, String tokenId) {
        Gameproject gameproject = gameprojectDao.selectById(gpId);
        if(ObjectUtil.isEmpty(gameproject)){
            return ResponseDataUtil.buildError("The gpId transmission is abnormal");
        }
        if(gameproject.getSpecies().equals("defi")){
            return ResponseDataUtil.buildSuccess(gameproject.getImgUrl());
        }else {
            return ResponseDataUtil.buildSuccess(gameproject.getImgUrl());
        }
    }

    @Override
    public ResponseData getPrice(String gpId) {
        Gameproject gameproject = gameprojectDao.selectById(gpId);
        if(ObjectUtil.isEmpty(gameproject)){
            return ResponseDataUtil.buildError("The gpId transmission is abnormal");
        }
        Object o = redisTemplate.opsForValue().get(gameproject.getName().toLowerCase()+"price");
        BigDecimal price = BigDecimal.valueOf(0);
        if(ObjectUtil.isNotEmpty(o)){
            price = new BigDecimal(o.toString());
        }
        return ResponseDataUtil.buildSuccess(price);
    }

    @Override
    public ResponseData uploadGameIcon(Gameicon gameicon) {
        gameicon.setGiId(IdUtil.simpleUUID());
        int i = gameiconDao.insert(gameicon);
        if(i < 1){
            return ResponseDataUtil.buildError();
        }
        return ResponseDataUtil.buildSuccess();
    }

    @Override
    public Object normal(String address, String start, String limit) {
        Normal normal = TokenUtil.normal(address,start,limit);
        if(ObjectUtil.isNotEmpty(normal) && ObjectUtil.isNotEmpty(normal.getData())){
            QueryWrapper<Gameproject> wrapper = new QueryWrapper<>();
            wrapper.eq("tokenHash",address);
            Gameproject gameproject = gameprojectDao.selectOne(wrapper);
            String img = "";
            if(gameproject.getSpecies().equals("defi")){
                img = gameproject.getImgUrl();
            }
            for(com.yike.apis.utils.tokenView.vo.normal.Data data:normal.getData()){
                if(img.equals("")){
                    data.setImgUrl(img);
                }else {
                    data.setImgUrl(img);
                }
            }
        }
        return ResponseDataUtil.buildSuccess(normal);
    }

    @Override
    public ResponseData getGame(String gpId) {
        Gameproject gameproject = gameprojectDao.selectById(gpId);
        return ResponseDataUtil.buildSuccess(gameproject);
    }

    @Override
    public ResponseData commentLiked(CommentLikedVo commentLikedVo) {
        if(ObjectUtil.isNotEmpty(commentLikedVo) && commentLikedVo.getStatus().equals(1)){
            Boolean bo = redisTemplate.opsForHash().hasKey(commentLikedVo.getGrId(),commentLikedVo.getUId());
            if(bo){
                return ResponseDataUtil.buildError("Please do not double like");
            }
            Double b = redisUtil.score(comment_liked_key,commentLikedVo.getGrId());
            if(ObjectUtil.isNotEmpty(b)){
                redisUtil.incrScore(comment_liked_key, commentLikedVo.getGrId(), 1D);
            }else {
                redisUtil.add(comment_liked_key,commentLikedVo.getGrId(),1D);
            }
            redisTemplate.opsForHash().put(commentLikedVo.getGrId(),commentLikedVo.getUId(),String.valueOf(commentLikedVo.getStatus()));
        }else {
            Double b = redisUtil.score(comment_liked_key,commentLikedVo.getGrId());
            if(ObjectUtil.isNotEmpty(b)){
                redisUtil.incrScore(comment_liked_key, commentLikedVo.getGrId(), -1D);
            }else {
                redisUtil.add(comment_liked_key,commentLikedVo.getGrId(),1D);
            }
            redisTemplate.opsForHash().delete(commentLikedVo.getGrId(),commentLikedVo.getUId());
        }
        return ResponseDataUtil.buildSuccess();
    }

    @Override
    public void commentLikedDataSynchronization() {
        List<Gameremark> gameremarks = gameremarkDao.selectList(null);
        if(ObjectUtil.isNotEmpty(gameremarks)){
            for(Gameremark gameremark:gameremarks){
                Double b = redisUtil.score(comment_liked_key,gameremark.getGrId());
                gameremark.setLiked(ObjectUtil.isNotEmpty(b) ? b.longValue() : 0L);
                gameremarkDao.updateById(gameremark);
            }
        }
    }

    @Override
    public ResponseData tokenTokentrans(String tokenAddress, String start, String limit) {
        TokenTokentrans tokenTokentrans = TokenUtil.tokenTokentrans(tokenAddress,start,limit);
        if(ObjectUtil.isNotEmpty(tokenTokentrans) && ObjectUtil.isNotEmpty(tokenTokentrans.getData())){
            QueryWrapper<Gameproject> wrapper = new QueryWrapper<>();
            wrapper.eq("tokenHash",tokenAddress);
            Gameproject gameproject = gameprojectDao.selectOne(wrapper);
            String img = "";
            img = gameproject.getImgUrl();
            Maincoinex maincoinex = TokenUtil.maincoinexchange();
            for(Data data:tokenTokentrans.getData()){
                if(data.getImageUrl().equals("not found") || data.getImageUrl().equals("") || ObjectUtil.isEmpty(data.getImageUrl())){
                    data.setImageUrl(img);
                }
                if(ObjectUtil.isNotEmpty(data.getValueIsNft())){
                    TxEth txEth = TokenUtil.txeth(data.getTxid());
                    if(ObjectUtil.isNotEmpty(txEth) && ObjectUtil.isNotEmpty(txEth.getData()) && txEth.getCode().equals(1)){
                        data.setEthValue(txEth.getData().getValue());
                    }else {
                        data.setEthValue("0");
                    }
                }else {
                    BigDecimal b = new BigDecimal(gameproject.getPrice()).multiply(new BigDecimal(data.getValue()).divide(BigDecimal.TEN.pow(new BigDecimal(data.getTokenInfo().getD()).intValue()),8,BigDecimal.ROUND_DOWN));
                    data.setEthValue(b.divide(new BigDecimal(maincoinex.getData().getEth()),8,BigDecimal.ROUND_DOWN).toPlainString());
                }
            }
        }
        return ResponseDataUtil.buildSuccess(tokenTokentrans);
    }

    @Override
    public ResponseData delUserSetGameIcon(GameiconGameprojectUser gameiconGameprojectUser) {
        if(ObjectUtil.isEmpty(gameiconGameprojectUser) || ObjectUtil.isEmpty(gameiconGameprojectUser.getUId()) || ObjectUtil.isEmpty(gameiconGameprojectUser.getGpId()) || ObjectUtil.isEmpty(gameiconGameprojectUser.getGiId())){
            return ResponseDataUtil.buildError("The value anomaly");
        }
        QueryWrapper<GameiconGameprojectUser> wrapper = new QueryWrapper<>();
        wrapper.eq("giId",gameiconGameprojectUser.getGiId());
        wrapper.eq("uId",gameiconGameprojectUser.getUId());
        wrapper.eq("gpId",gameiconGameprojectUser.getGpId());
        GameiconGameprojectUser gameiconGameprojectUser1 = gameiconGameprojectUserDao.selectOne(wrapper);
        if(ObjectUtil.isEmpty(gameiconGameprojectUser1)){
            return ResponseDataUtil.buildError("The value anomaly");
        }
        int i = gameiconGameprojectUserDao.deleteById(gameiconGameprojectUser1.getGiuId());
        if(i < 1){
            return ResponseDataUtil.buildError();
        }
        return ResponseDataUtil.buildSuccess();
    }

}
