package com.yike.apis.utils.Coinmarketcap;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yike.apis.utils.Coinmarketcap.vo.defi.CmcDate;
import com.yike.apis.utils.Coinmarketcap.vo.nft.NftData;

public class CmcUtil {
    /**
     * Obtaining CMC Data
     * @param start   Number of pages
     * @param limit   A number of
     * @param tagSlugs    tagSlugs：defi，collectibles-nfts，metaverse
     * @return
     */
    public static CmcDate getCmcDate(String start, String limit, String tagSlugs){
        String str = HttpUtil.get(String.format(Coinmarketcap.cryptocurrency,start,limit,tagSlugs));
        CmcDate cmcDate = new CmcDate();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            cmcDate = JSONUtil.toBean(str,CmcDate.class);
            if(cmcDate.getStatus().getErrorMessage().equalsIgnoreCase("SUCCESS")){
                flang = true;
            }
        }
        return cmcDate;
    }

    public static NftData getCmcNFTDate(String start, String limit){
        String str = HttpUtil.get(String.format(Coinmarketcap.nft,start,limit));
        NftData nftData = new NftData();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            nftData = JSONUtil.toBean(str,NftData.class);
            if(nftData.getStatus().getErrorMessage().equalsIgnoreCase("SUCCESS")){
                flang = true;
            }
        }
        return nftData;
    }

    public static void main(String[] args) {
//        Long i = System.currentTimeMillis();
//        CmcDate cmcDate = getCmcDate("1","2","collectibles-nfts");
//        cmcDate = getCmcDate("1",cmcDate.getData().getTotalCount(), "collectibles-nfts");
//        List<CryptoCurrencyList> list = cmcDate.getData().getCryptoCurrencyList();
//        list = list.stream().filter(s -> ObjectUtil.isNotEmpty(s.getQuotes()) && ObjectUtil.isNotEmpty(s.getQuotes().get(2)) && ObjectUtil.isNotEmpty(s.getQuotes().get(2).getMarketCap())).collect(Collectors.toList());
//        Double suma =  list.stream().map(e -> e.getQuotes().get(2).getMarketCap()).reduce(Double::sum).get();
//        Double a =  list.stream().map(e -> e.getQuotes().get(2).getVolume24h()).reduce(Double::sum).get();
//        Double new_circulatingSupply = list.stream().map(e -> e.getCirculatingSupply()).reduce(Double::sum).get();
//        Long j = System.currentTimeMillis();
//        System.out.println(BigDecimal.valueOf(new_circulatingSupply).toPlainString());
////        System.out.println(BigDecimal.valueOf(suma).toPlainString());
////        System.out.println(BigDecimal.valueOf(a).toPlainString());
//        System.out.println(j-i);
        System.out.println(36531774602107352L - 36531774046159720L);
    }
}
