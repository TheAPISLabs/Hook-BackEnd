package com.yike.apis.utils.feixiaohao;

public class FeiXiaoHaoApi {
    final static String websearch = "https://dncapi.xxbitcoin.xyz/api/search/websearch?page=1&exchange_page=1&wallet_page=1&pagesize=25&code=%s&token=&webp=1";
    final static String charts = "https://dncapi.xxbitcoin.xyz/api/coin/web-charts?code=%s&type=3m&webp=1";
    final static String holders = "https://dncapi.xxbitcoin.xyz/api/v3/coin/holders?code=%s&webp=1";
}
