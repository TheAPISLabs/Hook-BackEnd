package com.yike.apis.utils.tokenView.vo.Websearch.txeth;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data
 */
@NoArgsConstructor
@lombok.Data
public class Data {
    /**
     * appendixData
     */
    private AppendixData appendixData;
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
     * blockHash
     */
    private String blockHash;
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
     * toIsContract
     */
    private Integer toIsContract;
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
     * inputData
     */
    private String inputData;
    /**
     * gasUsed
     */
    private Integer gasUsed;
    /**
     * callTransfer
     */
    private List<CallTransfer> callTransfer;
    /**
     * tokenTransfer
     */
    private List<TokenTransfer> tokenTransfer;
    /**
     * maxFeePerGas
     */
    private Long maxFeePerGas;
    /**
     * maxPriorityFeePerGas
     */
    private Integer maxPriorityFeePerGas;
    /**
     * baseFeePerGas
     */
    private Long baseFeePerGas;
    /**
     * functionName
     */
    private String functionName;
}
