package com.yike.apis.utils.tokenView.vo.tokenTokentrans;

import lombok.NoArgsConstructor;

/**
 * Data
 */
@NoArgsConstructor
@lombok.Data
public class Data {
    /**
     * index
     */
    private Integer index;
    /**
     * blockNo
     */
    private Integer blockNo;
    /**
     * token
     */
    private String token;
    /**
     * tokenAddr
     */
    private String tokenAddr;
    /**
     * tokenSymbol
     */
    private String tokenSymbol;
    /**
     * tokenDecimals
     */
    private String tokenDecimals;
    /**
     * time
     */
    private Integer time;
    /**
     * txid
     */
    private String txid;
    /**
     * tokenInfo
     */
    private TokenInfo tokenInfo;
    /**
     * confirmations
     */
    private Integer confirmations;
    /**
     * from
     */
    private String from;
    /**
     * to
     */
    private String to;
    /**
     * value
     */
    private String value;
    /**
     * ethvalue
     */
    private String ethValue;
    /**
     * valueIsNft
     */
    private String valueIsNft;
    /**
     * functionName
     */
    private String functionName;
    /**
     * imageUrl
     */
    private String imageUrl;
}
