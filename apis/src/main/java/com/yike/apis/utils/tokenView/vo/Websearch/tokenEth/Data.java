package com.yike.apis.utils.tokenView.vo.Websearch.tokenEth;

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
     * tokenHash
     */
    private String tokenHash;
    /**
     * transferCnt
     */
    private Integer transferCnt;
    /**
     * holderCnt
     */
    private Integer holderCnt;
    /**
     * tokenInfo
     */
    private TokenInfo tokenInfo;
}
