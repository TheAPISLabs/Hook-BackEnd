package com.yike.apis.utils.tokenView.vo.address;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Txs
 */
@NoArgsConstructor
@Data
public class Txs {
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
     * functionName
     */
    private String functionName;
}
