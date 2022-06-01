package com.yike.apis.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.yike.apis.dao.MarketcapitalizationDao;
import com.yike.apis.pojo.mini.Marketcapitalization;
import com.yike.apis.pojo.mini.vo.HighestPriceStats;
import com.yike.apis.service.MiniService;
import com.yike.apis.utils.Coinmarketcap.CmcUtil;
import com.yike.apis.utils.Coinmarketcap.vo.defi.CmcDate;
import com.yike.apis.utils.Coinmarketcap.vo.defi.CryptoCurrencyList;
import com.yike.apis.utils.Coinmarketcap.vo.nft.Collections;
import com.yike.apis.utils.Coinmarketcap.vo.nft.NftData;
import com.yike.apis.utils.glassnode.GlaUtil;
import com.yike.apis.utils.glassnode.vo.GlaData;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MiniServiceImpl implements MiniService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MarketcapitalizationDao marketcapitalizationDao;

    @Override
    @Async
    public void setMarketcapitalizationData(String tagSlugs) {
//        redisTemplate.delete("highestPriceStats"+tagSlugs);
//        redisTemplate.delete("highestPriceStats"+tagSlugs+"_calculate");
        Long time = System.currentTimeMillis();
        CmcDate cmcDate = CmcUtil.getCmcDate("1","2",tagSlugs);
        cmcDate = CmcUtil.getCmcDate("1",cmcDate.getData().getTotalCount(), tagSlugs);
        List<CryptoCurrencyList> list = cmcDate.getData().getCryptoCurrencyList();
        for(CryptoCurrencyList cryptoCurrencyList:list){
            redisTemplate.opsForValue().set(cryptoCurrencyList.getName().toLowerCase(),cryptoCurrencyList);
        }
        list = list.stream().filter(s -> ObjectUtil.isNotEmpty(s.getQuotes()) && ObjectUtil.isNotEmpty(s.getQuotes().get(2)) && ObjectUtil.isNotEmpty(s.getQuotes().get(2).getMarketCap())).collect(Collectors.toList());
        Double marketCap =  list.stream().map(e -> e.getQuotes().get(2).getMarketCap()).reduce(Double::sum).get();
        Double volume =  list.stream().map(e -> e.getQuotes().get(2).getVolume24h()).reduce(Double::sum).get();
        //Keep 2 decimal places
        BigDecimal new_marketCap = BigDecimal.valueOf(marketCap).setScale(2,BigDecimal.ROUND_DOWN);
        BigDecimal new_volume = BigDecimal.valueOf(volume).setScale(2,BigDecimal.ROUND_DOWN);

        //Get the last data
        Object old_data = redisTemplate.opsForValue().get("highestPriceStats"+tagSlugs);
        Object old_data_calculate = redisTemplate.opsForValue().get("highestPriceStats"+tagSlugs+"_calculate");
        HighestPriceStats highestPriceStats_old_data = null;
        HighestPriceStats highestPriceStats_old_data_calculate = null;

        //Floating population
        BigDecimal marketCap_new = BigDecimal.valueOf(0);
        BigDecimal volume_new = BigDecimal.valueOf(0);
        //A floating rate
        BigDecimal marketCapRatio = new BigDecimal(0);
        BigDecimal volumeRatio = new BigDecimal(0);
        if(ObjectUtil.isNotEmpty(old_data_calculate) && ObjectUtil.isNotEmpty(old_data)){
            highestPriceStats_old_data = (HighestPriceStats) old_data;
            highestPriceStats_old_data_calculate = (HighestPriceStats) old_data_calculate;

            //Calculate the number
            marketCap_new = new_marketCap.abs();
            volume_new = new_volume.abs();
            //Calculate float rate
            marketCapRatio = marketCap_new.subtract(highestPriceStats_old_data_calculate.getMarketCap()).divide(highestPriceStats_old_data_calculate.getMarketCap(),4,BigDecimal.ROUND_DOWN);
            volumeRatio = volume_new.subtract(highestPriceStats_old_data_calculate.getVolume()).divide(highestPriceStats_old_data_calculate.getVolume(),4,BigDecimal.ROUND_DOWN);
        }else {
            //Calculate the number
            marketCap_new = new_marketCap;
            volume_new = new_volume;
        }
        HighestPriceStats highestPriceStats = new HighestPriceStats();
        highestPriceStats.setMarketCap(marketCap_new);
        highestPriceStats.setMarketCapRatio(marketCapRatio);
        highestPriceStats.setVolume(volume_new);
        highestPriceStats.setVolumeRatio(volumeRatio);
        highestPriceStats.setCirculatingSupply(new BigDecimal(0));
        highestPriceStats.setCirculatingSupplyRatio(new BigDecimal(0));

        HighestPriceStats highestPriceStats1 = new HighestPriceStats();
        highestPriceStats1.setMarketCap(new_marketCap);
        highestPriceStats1.setVolume(new_volume);
        highestPriceStats1.setCirculatingSupply(new BigDecimal(0));
        //Deposited in the cache
        redisTemplate.opsForValue().set("highestPriceStats"+tagSlugs,highestPriceStats1);
        redisTemplate.opsForValue().set("highestPriceStats"+tagSlugs+"_calculate",highestPriceStats);
        //In the mysql
        Marketcapitalization marketcapitalization = new Marketcapitalization();
        marketcapitalization.setMarketCap(marketCap_new.doubleValue());
        marketcapitalization.setVolume(volume_new.doubleValue());
        marketcapitalization.setCirculatingSupply(0D);
        marketcapitalization.setTime(time);
        marketcapitalization.setMcId(IdUtil.simpleUUID());
        if(tagSlugs.equalsIgnoreCase("defi")){
            marketcapitalization.setType(2);
        }else if(tagSlugs.equalsIgnoreCase("metaverse")){
            marketcapitalization.setType(3);
        }else {
            marketcapitalization.setType(-1);
        }
        marketcapitalizationDao.insert(marketcapitalization);

    }

    @Override
    @Async
    public void getGlassnode(String name, String url, String a,String cookie) {
        List<GlaData> list = GlaUtil.getGlassnode(url,a,cookie);
        if(ObjectUtil.isNotEmpty(list)){
            redisTemplate.delete(name+a);
            redisTemplate.opsForList().leftPushAll(name+a,list);
        }
    }

    @Override
    public void setFigureData() {
//        List<Marketcapitalization> marketcapitalization1_NFT = marketcapitalizationDao.getStatisticsByPeriodTime(1,1000 * 60 * 10L);
//        redisTemplate.opsForValue().set("NFT10m",marketcapitalization1_NFT);
//        List<Marketcapitalization> marketcapitalization2_NFT = marketcapitalizationDao.getStatisticsByPeriodTime(1,1000 * 60 * 30L);
//        redisTemplate.opsForValue().set("NFT30m",marketcapitalization2_NFT);
//        List<Marketcapitalization> marketcapitalization3_NFT = marketcapitalizationDao.getStatisticsByPeriodTime(1,1000 * 60 * 60L);
//        redisTemplate.opsForValue().set("NFT1h",marketcapitalization3_NFT);
//        List<Marketcapitalization> marketcapitalization4_NFT = marketcapitalizationDao.getStatisticsByPeriodTime(1,1000 * 60 * 60 * 4L);
//        redisTemplate.opsForValue().set("NFT4h",marketcapitalization4_NFT);
//        List<Marketcapitalization> marketcapitalization5_NFT = marketcapitalizationDao.getStatisticsByPeriodTime(1,1000 * 60 * 60 * 6L);
//        redisTemplate.opsForValue().set("NFT6h",marketcapitalization5_NFT);
//        List<Marketcapitalization> marketcapitalization6_NFT = marketcapitalizationDao.getStatisticsByPeriodTime(1,1000 * 60 * 60 * 12L);
//        redisTemplate.opsForValue().set("NFT12h",marketcapitalization6_NFT);
        List<Marketcapitalization> marketcapitalization7_NFT = marketcapitalizationDao.getStatisticsByPeriodTime(1,1000 * 60 * 60 * 24L);
        redisTemplate.opsForValue().set("NFT1d",marketcapitalization7_NFT);
        List<Marketcapitalization> marketcapitalization8_NFT = marketcapitalizationDao.getStatisticsByPeriodTime(1,1000 * 60 * 60 * 24 * 3L);
        redisTemplate.opsForValue().set("NFT3d",marketcapitalization8_NFT);
        List<Marketcapitalization> marketcapitalization9_NFT = marketcapitalizationDao.getStatisticsByPeriodTime(1,1000 * 60 * 60 * 24 * 7L);
        redisTemplate.opsForValue().set("NFT7d",marketcapitalization9_NFT);
        List<Marketcapitalization> marketcapitalization10_NFT = marketcapitalizationDao.getStatisticsByPeriodTime(1,1000 * 60 * 60 * 24 * 30L);
        redisTemplate.opsForValue().set("NFT30d",marketcapitalization10_NFT);

//        List<Marketcapitalization> marketcapitalization1_Defi = marketcapitalizationDao.getStatisticsByPeriodTime(2,1000 * 60 * 10L);
//        redisTemplate.opsForValue().set("Defi10m",marketcapitalization1_Defi);
//        List<Marketcapitalization> marketcapitalization2_Defi = marketcapitalizationDao.getStatisticsByPeriodTime(2,1000 * 60 * 30L);
//        redisTemplate.opsForValue().set("Defi30m",marketcapitalization2_Defi);
//        List<Marketcapitalization> marketcapitalization3_Defi = marketcapitalizationDao.getStatisticsByPeriodTime(2,1000 * 60 * 60L);
//        redisTemplate.opsForValue().set("Defi1h",marketcapitalization3_Defi);
//        List<Marketcapitalization> marketcapitalization4_Defi = marketcapitalizationDao.getStatisticsByPeriodTime(2,1000 * 60 * 60 * 4L);
//        redisTemplate.opsForValue().set("Defi4h",marketcapitalization4_Defi);
//        List<Marketcapitalization> marketcapitalization5_Defi = marketcapitalizationDao.getStatisticsByPeriodTime(2,1000 * 60 * 60 * 6L);
//        redisTemplate.opsForValue().set("Defi6h",marketcapitalization5_Defi);
//        List<Marketcapitalization> marketcapitalization6_Defi = marketcapitalizationDao.getStatisticsByPeriodTime(2,1000 * 60 * 60 * 12L);
//        redisTemplate.opsForValue().set("Defi12h",marketcapitalization6_Defi);
        List<Marketcapitalization> marketcapitalization7_Defi = marketcapitalizationDao.getStatisticsByPeriodTime(2,1000 * 60 * 60 * 24L);
        redisTemplate.opsForValue().set("Defi1d",marketcapitalization7_Defi);
        List<Marketcapitalization> marketcapitalization8_Defi = marketcapitalizationDao.getStatisticsByPeriodTime(2,1000 * 60 * 60 * 24 * 3L);
        redisTemplate.opsForValue().set("Defi3d",marketcapitalization8_Defi);
        List<Marketcapitalization> marketcapitalization9_Defi = marketcapitalizationDao.getStatisticsByPeriodTime(2,1000 * 60 * 60 * 24 * 7L);
        redisTemplate.opsForValue().set("Defi7d",marketcapitalization9_Defi);
        List<Marketcapitalization> marketcapitalization10_Defi = marketcapitalizationDao.getStatisticsByPeriodTime(2,1000 * 60 * 60 * 24 * 30L);
        redisTemplate.opsForValue().set("Defi30d",marketcapitalization10_Defi);

//        List<Marketcapitalization> marketcapitalization1_metaverse = marketcapitalizationDao.getStatisticsByPeriodTime(3,1000 * 60 * 10L);
//        redisTemplate.opsForValue().set("metaverse10m",marketcapitalization1_metaverse);
//        List<Marketcapitalization> marketcapitalization2_metaverse = marketcapitalizationDao.getStatisticsByPeriodTime(3,1000 * 60 * 30L);
//        redisTemplate.opsForValue().set("metaverse30m",marketcapitalization2_metaverse);
//        List<Marketcapitalization> marketcapitalization3_metaverse = marketcapitalizationDao.getStatisticsByPeriodTime(3,1000 * 60 * 60L);
//        redisTemplate.opsForValue().set("metaverse1h",marketcapitalization3_metaverse);
//        List<Marketcapitalization> marketcapitalization4_metaverse = marketcapitalizationDao.getStatisticsByPeriodTime(3,1000 * 60 * 60 * 4L);
//        redisTemplate.opsForValue().set("metaverse4h",marketcapitalization4_metaverse);
//        List<Marketcapitalization> marketcapitalization5_metaverse = marketcapitalizationDao.getStatisticsByPeriodTime(3,1000 * 60 * 60 * 6L);
//        redisTemplate.opsForValue().set("metaverse6h",marketcapitalization5_metaverse);
//        List<Marketcapitalization> marketcapitalization6_metaverse = marketcapitalizationDao.getStatisticsByPeriodTime(3,1000 * 60 * 60 * 12L);
//        redisTemplate.opsForValue().set("metaverse12h",marketcapitalization6_metaverse);
        List<Marketcapitalization> marketcapitalization7_metaverse = marketcapitalizationDao.getStatisticsByPeriodTime(3,1000 * 60 * 60 * 24L);
        redisTemplate.opsForValue().set("metaverse1d",marketcapitalization7_metaverse);
        List<Marketcapitalization> marketcapitalization8_metaverse = marketcapitalizationDao.getStatisticsByPeriodTime(3,1000 * 60 * 60 * 24 * 3L);
        redisTemplate.opsForValue().set("metaverse3d",marketcapitalization8_metaverse);
        List<Marketcapitalization> marketcapitalization9_metaverse = marketcapitalizationDao.getStatisticsByPeriodTime(3,1000 * 60 * 60 * 24 * 7L);
        redisTemplate.opsForValue().set("metaverse7d",marketcapitalization9_metaverse);
        List<Marketcapitalization> marketcapitalization10_metaverse = marketcapitalizationDao.getStatisticsByPeriodTime(3,1000 * 60 * 60 * 24 * 30L);
        redisTemplate.opsForValue().set("metaverse30d",marketcapitalization10_metaverse);

    }

    @Override
    public ResponseData getMarketCapitalization(String type) {
        Object old_data_calculate = null;
        if(type.equalsIgnoreCase("NFT")){
            old_data_calculate = redisTemplate.opsForValue().get("highestPriceStatsNFT_calculate");
        }else if(type.equalsIgnoreCase("Defi")){
            old_data_calculate = redisTemplate.opsForValue().get("highestPriceStatsdefi_calculate");
        }else if(type.equalsIgnoreCase("metaverse")){
            old_data_calculate = redisTemplate.opsForValue().get("highestPriceStatsmetaverse_calculate");
        }
        return ResponseDataUtil.buildSuccess(old_data_calculate);
    }

    @Override
    public ResponseData getFigureData(String type, String time) {
        if(ObjectUtil.isEmpty(time)){
            time = "1d";
        }
        if(ObjectUtil.isEmpty(type)){
            Map map = new HashMap();
            Object NFT = redisTemplate.opsForValue().get("NFT"+time);
            Object Defi = redisTemplate.opsForValue().get("Defi"+time);
            Object metaverse = redisTemplate.opsForValue().get("metaverse"+time);
            if(ObjectUtil.isNotEmpty(NFT)){
                List<Marketcapitalization> NFT_new = (List<Marketcapitalization>) NFT;
                NFT_new = NFT_new.subList(0,NFT_new.size() > 30 ? 30 : NFT_new.size());
                List<Marketcapitalization> Defi_new = (List<Marketcapitalization>) Defi;
                Defi_new = Defi_new.subList(0,Defi_new.size() > 30 ? 30 : Defi_new.size());
                List<Marketcapitalization> metaverse_new = (List<Marketcapitalization>) metaverse;
                metaverse_new = metaverse_new.subList(0,metaverse_new.size() > 30 ? 30 : metaverse_new.size());
                Map map1 = new HashMap();
                map1.put("volume",NFT_new.stream().map(e -> e.getVolume()));
                map1.put("marketCap",NFT_new.stream().map(e -> e.getMarketCap()));
                map1.put("sales",NFT_new.stream().map(e -> e.getCirculatingSupply()));

                Map map2 = new HashMap();
                map2.put("volume",Defi_new.stream().map(e -> e.getVolume()));
                map2.put("marketCap",Defi_new.stream().map(e -> e.getMarketCap()));
                map2.put("sales",Defi_new.stream().map(e -> e.getCirculatingSupply()));

                Map map3 = new HashMap();
                map3.put("volume",metaverse_new.stream().map(e -> e.getVolume()));
                map3.put("marketCap",metaverse_new.stream().map(e -> e.getMarketCap()));
                map3.put("sales",metaverse_new.stream().map(e -> e.getCirculatingSupply()));

                Map map4 = new HashMap();
                map4.put("time",NFT_new.stream().map(e -> e.getTime()));

                map.put("NFT",map1);
                map.put("DEFI",map2);
                map.put("GAMEFI",map3);
                map.put("time",map4);
            }
            return ResponseDataUtil.buildSuccess(map);
        }else {
            Object o = redisTemplate.opsForValue().get(type+time);
            return ResponseDataUtil.buildSuccess(o);
        }
    }

    @Override
    public ResponseData getGlassnodeFigureData(String methods, String type) {
        if(ObjectUtil.isEmpty(methods)){
            methods = "price";
        }
        if(ObjectUtil.isEmpty(type)){
            type = "BTC";
        }
        List<GlaData> o = redisTemplate.opsForList().range(methods+type,0,-1);
        return ResponseDataUtil.buildSuccess(o);
    }

    @Override
    public void setMarketcapitalizationDataNFT() {
//        redisTemplate.delete("highestPriceStatsNFT");
//        redisTemplate.delete("highestPriceStatsNFT"+"_calculate");
        Long time = System.currentTimeMillis();
        NftData nftData = CmcUtil.getCmcNFTDate("0","2");
        nftData = CmcUtil.getCmcNFTDate("0",nftData.getData().getCount());
        List<Collections> list = nftData.getData().getCollections();
        for(Collections collections:list){
            redisTemplate.opsForValue().set(collections.getName().toLowerCase(),collections);
        }
        list = list.stream().filter(s -> ObjectUtil.isNotEmpty(s) && ObjectUtil.isNotEmpty(s.getMarketCapUsd()) && s.getMarketCapUsd() != null).collect(Collectors.toList());
        Double marketCap =  list.stream().map(e -> e.getMarketCapUsd()).reduce(Double::sum).get();
        Double volume =  list.stream().map(e -> e.getOneDay().getVolumeUsd()).reduce(Double::sum).get();
        Integer circulatingSupply =  list.stream().map(e -> e.getOneDay().getSales()).reduce(Integer::sum).get();

        //Keep 2 decimal places
        BigDecimal new_marketCap = BigDecimal.valueOf(marketCap).setScale(2,BigDecimal.ROUND_DOWN);
        BigDecimal new_volume = BigDecimal.valueOf(volume).setScale(2,BigDecimal.ROUND_DOWN);
        BigDecimal new_circulatingSupply = BigDecimal.valueOf(circulatingSupply).setScale(8,BigDecimal.ROUND_DOWN);;

        //Get the last data
        Object old_data = redisTemplate.opsForValue().get("highestPriceStatsNFT");
        Object old_data_calculate = redisTemplate.opsForValue().get("highestPriceStatsNFT"+"_calculate");
        HighestPriceStats highestPriceStats_old_data = null;
        HighestPriceStats highestPriceStats_old_data_calculate = null;

        //Floating population
        BigDecimal marketCap_new = BigDecimal.valueOf(0);
        BigDecimal volume_new = BigDecimal.valueOf(0);
        BigDecimal circulatingSupply_new = BigDecimal.valueOf(0);
        //A floating rate
        BigDecimal marketCapRatio = new BigDecimal(0);
        BigDecimal volumeRatio = new BigDecimal(0);
        BigDecimal circulatingSupplyRatio = new BigDecimal(0);
        if(ObjectUtil.isNotEmpty(old_data_calculate) && ObjectUtil.isNotEmpty(old_data)){
            highestPriceStats_old_data = (HighestPriceStats) old_data;
            highestPriceStats_old_data_calculate = (HighestPriceStats) old_data_calculate;

            //Calculate the number
            marketCap_new = new_marketCap.abs();
            volume_new = new_volume.abs();
            circulatingSupply_new = new_circulatingSupply.abs();
            //Calculate float rate
            marketCapRatio = marketCap_new.subtract(highestPriceStats_old_data_calculate.getMarketCap()).divide(highestPriceStats_old_data_calculate.getMarketCap(),4,BigDecimal.ROUND_DOWN);
            volumeRatio = volume_new.subtract(highestPriceStats_old_data_calculate.getVolume()).divide(highestPriceStats_old_data_calculate.getVolume(),4,BigDecimal.ROUND_DOWN);
            circulatingSupplyRatio = circulatingSupply_new.subtract(highestPriceStats_old_data_calculate.getCirculatingSupply()).divide(highestPriceStats_old_data_calculate.getCirculatingSupply(),4,BigDecimal.ROUND_DOWN);
        }else {
            //Calculate the number
            marketCap_new = new_marketCap;
            volume_new = new_volume;
            circulatingSupply_new = new_circulatingSupply;
        }
        HighestPriceStats highestPriceStats = new HighestPriceStats();
        highestPriceStats.setMarketCap(marketCap_new);
        highestPriceStats.setMarketCapRatio(marketCapRatio);
        highestPriceStats.setVolume(volume_new);
        highestPriceStats.setVolumeRatio(volumeRatio);
        highestPriceStats.setCirculatingSupply(circulatingSupply_new.compareTo(new BigDecimal(0)) > 0 ? circulatingSupply_new : new BigDecimal(1));
        highestPriceStats.setCirculatingSupplyRatio(circulatingSupplyRatio);

        HighestPriceStats highestPriceStats1 = new HighestPriceStats();
        highestPriceStats1.setMarketCap(new_marketCap);
        highestPriceStats1.setVolume(new_volume);
        highestPriceStats1.setCirculatingSupply(new_circulatingSupply);
        //Deposited in the cache
        redisTemplate.opsForValue().set("highestPriceStatsNFT",highestPriceStats1);
        redisTemplate.opsForValue().set("highestPriceStatsNFT"+"_calculate",highestPriceStats);
        //In the mysql
        Marketcapitalization marketcapitalization = new Marketcapitalization();
        marketcapitalization.setMarketCap(marketCap_new.doubleValue());
        marketcapitalization.setVolume(volume_new.doubleValue());
        marketcapitalization.setCirculatingSupply(circulatingSupply_new.doubleValue());
        marketcapitalization.setTime(time);
        marketcapitalization.setMcId(IdUtil.simpleUUID());
        marketcapitalization.setType(1);
        marketcapitalizationDao.insert(marketcapitalization);
    }

    @Override
    public ResponseData getCmcData(String type,Integer start,Integer limit) {
        if(ObjectUtil.isEmpty(start)){
            start = 1;
        }
        if(ObjectUtil.isEmpty(limit)){
            limit = 5;
        }
        Map map = new HashMap();
        if(ObjectUtil.isEmpty(type)){
            NftData nftData = (NftData) redisTemplate.opsForValue().get("cmcNftData");
            Integer i = (start - 1) * limit;
            Integer j = (start - 1) * limit + limit;
            if(i >= nftData.getData().getCollections().size() - 1){
                i = nftData.getData().getCollections().size() - 1;
            }
            if(j >= nftData.getData().getCollections().size() - 1){
                j = nftData.getData().getCollections().size() - 1;
            }
            nftData.getData().setCollections(nftData.getData().getCollections().subList(i,j));
            CmcDate cmcDate = (CmcDate) redisTemplate.opsForValue().get("cmcDefiDate");
            Integer m = (start - 1) * limit;
            Integer n = (start - 1) * limit + limit;
            if(m >= cmcDate.getData().getCryptoCurrencyList().size() - 1){
                m = cmcDate.getData().getCryptoCurrencyList().size() - 1;
            }
            if(n >= cmcDate.getData().getCryptoCurrencyList().size() - 1){
                n = cmcDate.getData().getCryptoCurrencyList().size() - 1;
            }
            cmcDate.getData().setCryptoCurrencyList(cmcDate.getData().getCryptoCurrencyList().subList(m,n));
            map.put("NFT",nftData);
            map.put("Defi",cmcDate);
        }else if(type.equalsIgnoreCase("NFT")){
            NftData nftData = (NftData) redisTemplate.opsForValue().get("cmcNftData");
            Integer i = (start - 1) * limit;
            Integer j = (start - 1) * limit + limit;
            if(i >= nftData.getData().getCollections().size() - 1){
                i = nftData.getData().getCollections().size() - 1;
            }
            if(j >= nftData.getData().getCollections().size() - 1){
                j = nftData.getData().getCollections().size() - 1;
            }
            nftData.getData().setCollections(nftData.getData().getCollections().subList(i,j));
            map.put("NFT",nftData);
        }else if(type.equalsIgnoreCase("Defi")){
            CmcDate cmcDate = (CmcDate) redisTemplate.opsForValue().get("cmcDefiDate");
            Integer m = (start - 1) * limit;
            Integer n = (start - 1) * limit + limit;
            if(m >= cmcDate.getData().getCryptoCurrencyList().size() - 1){
                m = cmcDate.getData().getCryptoCurrencyList().size() - 1;
            }
            if(n >= cmcDate.getData().getCryptoCurrencyList().size() - 1){
                n = cmcDate.getData().getCryptoCurrencyList().size() - 1;
            }
            cmcDate.getData().setCryptoCurrencyList(cmcDate.getData().getCryptoCurrencyList().subList(m,n));
            map.put("Defi",cmcDate);
        }
        return ResponseDataUtil.buildSuccess(map);
    }

    @Override
    public void setCmcData() {
        NftData nftData = CmcUtil.getCmcNFTDate("0","2");
        nftData = CmcUtil.getCmcNFTDate("0",nftData.getData().getCount());
        CmcDate cmcDate = CmcUtil.getCmcDate("1","2","defi");
        cmcDate = CmcUtil.getCmcDate("1",cmcDate.getData().getTotalCount(),"defi");
        redisTemplate.opsForValue().set("cmcNftData",nftData);
        redisTemplate.opsForValue().set("cmcDefiDate",cmcDate);
    }
}
