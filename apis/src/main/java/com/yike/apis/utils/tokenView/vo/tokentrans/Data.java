package com.yike.apis.utils.tokenView.vo.tokentrans;

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
     * valueIsNft
     */
    private String valueIsNft;
    /**
     * amount
     */
    private String amount;
    /**
     * functionName
     */
    private String functionName;

    /**
     * price
     */
    private String price;

    /**
     * totalPrice
     */
    private String totalPrice;
}
