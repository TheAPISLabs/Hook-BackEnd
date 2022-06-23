package com.yike.apis.utils.tokenView.vo.address;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data
 */
@NoArgsConstructor
@lombok.Data
public class Data {
    /**
     * type
     */
    private String type;
    /**
     * network
     */
    private String network;
    /**
     * hash
     */
    private String hash;
    /**
     * rank
     */
    private Long rank;
    /**
     * spend
     */
    private String spend;
    /**
     * receive
     */
    private String receive;
    /**
     * balance
     */
    private String balance;
    /**
     * normalTxCount
     */
    private Integer normalTxCount;
    /**
     * txs
     */
    private List<Txs> txs;
    /**
     * addressType
     */
    private String addressType;
    /**
     * nonce
     */
    private Integer nonce;
}
