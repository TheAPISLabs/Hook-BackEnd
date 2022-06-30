package com.yike.apis.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yike.apis.dao.SearchHeatTagDao;
import com.yike.apis.dao.SearchTagDao;
import com.yike.apis.dao.SearchheatDao;
import com.yike.apis.pojo.search.SearchHeat;
import com.yike.apis.pojo.search.SearchHeatTag;
import com.yike.apis.pojo.search.SearchTag;
import com.yike.apis.service.TrachService;
import com.yike.apis.utils.Coinmarketcap.CmcUtil;
import com.yike.apis.utils.Coinmarketcap.vo.nft.Collections;
import com.yike.apis.utils.Coinmarketcap.vo.nft.NftData;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import com.yike.apis.utils.tokenView.TokenUtil;
import com.yike.apis.utils.tokenView.vo.Websearch.contract.Contract;
import com.yike.apis.utils.tokenView.vo.Websearch.normal.Normal;
import com.yike.apis.utils.tokenView.vo.Websearch.token.Data;
import com.yike.apis.utils.tokenView.vo.Websearch.token.Token;
import com.yike.apis.utils.tokenView.vo.Websearch.tokenbalance.TokenBalance;
import com.yike.apis.utils.tokenView.vo.Websearch.tokentrans.Tokentrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrachServiceImpl implements TrachService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SearchTagDao searchTagDao;

    @Autowired
    private SearchheatDao searchHeatDao;

    @Autowired
    private SearchHeatTagDao searchHeatTagDao;

    @Override
    public ResponseData tokenbalance(String address) {
        Map map = new HashMap();
        TokenBalance tokenBalance = TokenUtil.tokenbalance(address);
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal totalPrice_nft = new BigDecimal(0);
        BigDecimal totalPrice_defi = new BigDecimal(0);
        if(tokenBalance.getCode() == 1){
            for(int i = 0;i<tokenBalance.getData().size();i++){
                Object o = new Object();
                if(ObjectUtil.isEmpty(tokenBalance.getData().get(i).getTokenInfo().getC())){
                    o = redisTemplate.opsForValue().get(tokenBalance.getData().get(i).getTokenInfo().getH().toLowerCase()+"price");
                    tokenBalance.getData().get(i).setBalance(new BigDecimal(tokenBalance.getData().get(i).getBalance()).divide(BigDecimal.TEN.pow(new BigDecimal(tokenBalance.getData().get(i).getTokenInfo().getD()).intValue())).toPlainString());
                }else {
                    o = redisTemplate.opsForValue().get(tokenBalance.getData().get(i).getTokenInfo().getF().toLowerCase()+"price");
                }
                BigDecimal price = BigDecimal.valueOf(0);
                if(ObjectUtil.isNotEmpty(o) && !o.equals("NaN")){
                    price = new BigDecimal(o.toString());
                }
                tokenBalance.getData().get(i).setPrice(price.toPlainString());
                tokenBalance.getData().get(i).setTotalPrice(price.multiply(new BigDecimal(tokenBalance.getData().get(i).getBalance())).toPlainString());
                totalPrice = totalPrice.add(new BigDecimal(tokenBalance.getData().get(i).getTotalPrice()));
                if(ObjectUtil.isEmpty(tokenBalance.getData().get(i).getTokenInfo().getC())){
                    totalPrice_defi = totalPrice_defi.add(new BigDecimal(tokenBalance.getData().get(i).getTotalPrice()));
                }else {
                    totalPrice_nft = totalPrice_nft.add(new BigDecimal(tokenBalance.getData().get(i).getTotalPrice()));
                }
            }
        }
        map.put("tokenBalance",tokenBalance);
        map.put("totalPrice",totalPrice);
        map.put("totalPrice_nft",totalPrice_nft);
        map.put("totalPrice_defi",totalPrice_defi);
        return ResponseDataUtil.buildSuccess(map);
    }

    @Override
    public ResponseData tokentrans(String address, String tokenAddress, String start, String limit) {
        Tokentrans tokentrans = TokenUtil.tokentrans(address,tokenAddress,start,limit);
        if(tokentrans.getCode() == 1){
            for(int i = 0;i<tokentrans.getData().size();i++){
                Object o = new Object();
                if(ObjectUtil.isEmpty(tokentrans.getData().get(i).getTokenInfo().getC())){
                    o = redisTemplate.opsForValue().get(tokentrans.getData().get(i).getTokenInfo().getH().toLowerCase()+"price");
                }else {
                    o = redisTemplate.opsForValue().get(tokentrans.getData().get(i).getTokenInfo().getF().toLowerCase()+"price");
                }
                BigDecimal price = BigDecimal.valueOf(0);
                if(ObjectUtil.isNotEmpty(o)){
                    price = new BigDecimal(o.toString());
                }
                tokentrans.getData().get(i).setPrice(price.toPlainString());
                if(ObjectUtil.isEmpty(tokentrans.getData().get(i).getTokenInfo().getC())){
                    tokentrans.getData().get(i).setValue(new BigDecimal(tokentrans.getData().get(i).getValue()).divide(BigDecimal.TEN.pow(new BigDecimal(tokentrans.getData().get(i).getTokenDecimals()).intValue())).toPlainString());
                    tokentrans.getData().get(i).setTotalPrice(price.multiply(new BigDecimal(tokentrans.getData().get(i).getValue())).toPlainString());
                }else {
                    tokentrans.getData().get(i).setTotalPrice(price.multiply(new BigDecimal(tokentrans.getData().get(i).getValueIsNft())).toPlainString());
                }
            }
        }
        return ResponseDataUtil.buildSuccess(tokentrans);
    }

    @Override
    public void setPrice() {
        Token token = TokenUtil.gettokens();
        if(token.getData().getCode() == 1){
            List<Data> dataList = token.getData().getData();
            if(ObjectUtil.isNotEmpty(dataList)){
                for(Data data:dataList){
                    redisTemplate.opsForValue().set(data.getTokenaddress().toLowerCase()+"price",ObjectUtil.isNotEmpty(data.getPrice()) ? data.getPrice() : 0D);
                }
            }
        }
        NftData nftData = CmcUtil.getCmcNFTDate("0","2");
        nftData = CmcUtil.getCmcNFTDate("0",nftData.getData().getCount());
        if(ObjectUtil.isNotEmpty(nftData)){
            List<Collections> collectionsList = nftData.getData().getCollections();
            for(Collections collections:collectionsList){
                redisTemplate.opsForValue().set(collections.getName().toLowerCase()+"price",ObjectUtil.isNotEmpty(collections.getFloorPriceUsd()) ? collections.getFloorPriceUsd() : 0D);
            }
        }
    }

    @Override
    public Object maincoinexchange() {
        return TokenUtil.maincoinexchange();
    }

    @Override
    public Object normal(String address, String start, String limit) {
        return TokenUtil.normal(address,start,limit);
    }

    @Override
    public ResponseData getTag(String address) {
        List<SearchTag> tagList = searchTagDao.selectList(null);
        List<SearchTag> tagListAddress = searchTagDao.selectByAddress(address);
        List<SearchTag> notCommentTag = getNotComment(tagList,tagListAddress);
        if(notCommentTag.size() > 0){
            String nft = null;
            String opensea = null;
            String swapper = null;
            String project = null;
            for(SearchTag tag:notCommentTag){
                if(tag.getTagName().equalsIgnoreCase("NFT collector")){
                    nft = tag.getStId();
                }
                if(tag.getTagName().equalsIgnoreCase("Opensea")){
                    opensea = tag.getStId();
                }
                if(tag.getTagName().equalsIgnoreCase("Swapper")){
                    swapper = tag.getStId();
                }
                if(tag.getTagName().equalsIgnoreCase("Project")){
                    project = tag.getStId();
                }
            }
            Normal normal = TokenUtil.normal(address,"0","50000");
            QueryWrapper<SearchHeat> wrapper = new QueryWrapper<>();
            wrapper.eq("searchName",address);
            SearchHeat searchHeat = searchHeatDao.selectOne(wrapper);
            int i = 0;
            if(ObjectUtil.isNotEmpty(normal) && ObjectUtil.isNotEmpty(normal.getData())){
                for(com.yike.apis.utils.tokenView.vo.Websearch.normal.Data data:normal.getData()){
                    if(ObjectUtil.isEmpty(searchHeat) || (ObjectUtil.isNotEmpty(searchHeat.getBlockno()) && searchHeat.getBlockno() >= data.getBlockNo())){
                        break;
                    }
                    if(i >= notCommentTag.size()){
                        break;
                    }
                    if(ObjectUtil.isNotEmpty(nft)){
                        if(ObjectUtil.isNotEmpty(data.getTokenTransfer()) && ObjectUtil.isNotEmpty(data.getTokenTransfer().get(0).getTokenInfo()) && ObjectUtil.isNotEmpty(data.getTokenTransfer().get(0).getTokenInfo().getC())){
                            i++;
                            SearchHeatTag heatTag = new SearchHeatTag();
                            heatTag.setAddress(address);
                            heatTag.setShtId(IdUtil.simpleUUID());
                            heatTag.setStId(nft);
                            heatTag.setShId(searchHeat.getShId());
                            searchHeatTagDao.insert(heatTag);
                            nft = null;
                        }
                    }
                    if(ObjectUtil.isNotEmpty(opensea)){
                        if(data.getTo().equalsIgnoreCase("0x7f268357a8c2552623316e2562d90e642bb538e5")){
                            i++;
                            SearchHeatTag heatTag = new SearchHeatTag();
                            heatTag.setAddress(address);
                            heatTag.setShtId(IdUtil.simpleUUID());
                            heatTag.setStId(opensea);
                            heatTag.setShId(searchHeat.getShId());
                            searchHeatTagDao.insert(heatTag);
                            opensea = null;
                        }
                    }
                    if(ObjectUtil.isNotEmpty(swapper)){
                        if(data.getTo().equalsIgnoreCase("0x088ee5007c98a9677165d78dd2109ae4a3d04d0c") ||
                                data.getTo().equalsIgnoreCase("0x2fdbadf3c4d5a8666bc06645b8358ab803996e28")){
                            i++;

                            SearchHeatTag heatTag = new SearchHeatTag();
                            heatTag.setAddress(address);
                            heatTag.setShtId(IdUtil.simpleUUID());
                            heatTag.setStId(swapper);
                            heatTag.setShId(searchHeat.getShId());
                            searchHeatTagDao.insert(heatTag);
                            swapper = null;
                        }
                    }
                    if(ObjectUtil.isNotEmpty(project) && ObjectUtil.isNotEmpty(data.getToAlias())){
                        Contract contract = TokenUtil.contract(data.getTo());;
                        if(ObjectUtil.isNotEmpty(contract) && ObjectUtil.isNotEmpty(contract.getData()) && contract.getData().getCallTransfer().get(0).getFrom().equalsIgnoreCase(address)){
                            i++;
                            SearchHeatTag heatTag = new SearchHeatTag();
                            heatTag.setAddress(address);
                            heatTag.setShtId(IdUtil.simpleUUID());
                            heatTag.setStId(project);
                            heatTag.setShId(searchHeat.getShId());
                            searchHeatTagDao.insert(heatTag);
                            project = null;
                        }
                    }
                }
                searchHeat.setBlockno(Long.parseLong(String.valueOf(normal.getData().get(0).getBlockNo())));
                searchHeatDao.updateById(searchHeat);
            }
        }
        tagListAddress = searchTagDao.selectByAddress(address);
        return ResponseDataUtil.buildSuccess(tagListAddress);
    }

    public List<SearchTag> getNotComment(List<SearchTag> allTag,List<SearchTag> commentTag){
        List<SearchTag> notCommentTag = allTag.stream()
                .filter(notComment -> !commentTag.stream().map(all -> all.getStId()).collect(Collectors.toList()).contains(notComment.getStId()))
                .collect(Collectors.toList());
        return notCommentTag;
    }

}
