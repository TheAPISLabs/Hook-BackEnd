package com.yike.apis.utils.Coinmarketcap;

public class Coinmarketcap {
    final static String cryptocurrency = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?" +
            "start=%s&" +
            "limit=%s&" +
            "sortBy=market_cap&sortType=desc&convert=USD,BTC,ETH&cryptoType=all&tagType=all&audited=false&" +
            "aux=ath,atl,high24h,low24h,num_market_pairs,cmc_rank,date_added,tags,platform,max_supply,circulating_supply,self_reported_circulating_supply,self_reported_market_cap,total_supply,volume_7d,volume_30d&" +
            "tagSlugs=%S";

    final static String nft = "https://api.coinmarketcap.com/data-api/v3/nft/collections?start=%s&limit=%s&blockchain=Ethereum&sort=volume&desc=true&period=1";

    final static String chart = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/detail/chart?id=%s&range=3M";
}
