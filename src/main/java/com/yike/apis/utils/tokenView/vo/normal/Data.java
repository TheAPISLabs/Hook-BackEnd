package com.yike.apis.utils.tokenView.vo.normal;

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
     * blockNo
     */
    private Integer blockNo;
    /**
     * height
     */
    private Integer height;
    /**
     * index
     */
    private Integer index;
    /**
     * time
     */
    private Integer time;
    /**
     * txid
     */
    private String txid;
    /**
     * fee
     */
    private String fee;
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
     * nonce
     */
    private Integer nonce;
    /**
     * gasPrice
     */
    private Long gasPrice;
    /**
     * gasLimit
     */
    private Integer gasLimit;
    /**
     * value
     */
    private String value;
    /**
     * gasUsed
     */
    private Integer gasUsed;
    /**
     * maxFeePerGas
     */
    private Long maxFeePerGas;
    /**
     * maxPriorityFeePerGas
     */
    private Integer maxPriorityFeePerGas;
    /**
     * functionName
     */
    private String functionName;
    /**
     * toAlias
     */
    private String toAlias;
    /**
     * toIsContract
     */
    private Integer toIsContract;

    /**
     * imgUrl
     */
    private String imgUrl;

    /**
     * tokenTransfer
     */
    private List<TokenTransfer> tokenTransfer;
}
