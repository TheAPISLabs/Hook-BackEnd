package com.yike.apis.utils.tokenView.vo.Websearch.tokenbalance;

import lombok.NoArgsConstructor;

/**
 * Data
 */
@NoArgsConstructor
@lombok.Data
public class Data {
    /**
     * network
     */
    private String network;
    /**
     * hash
     */
    private String hash;
    /**
     * tokenInfo
     */
    private TokenInfo tokenInfo;
    /**
     * transferCnt
     */
    private Integer transferCnt;
    /**
     * balance
     */
    private String balance;

    /**
     * price
     */
    private String price;

    /**
     * totalPrice
     */
    private String totalPrice;
}
