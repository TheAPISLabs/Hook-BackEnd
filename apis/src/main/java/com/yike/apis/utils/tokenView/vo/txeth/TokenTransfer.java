package com.yike.apis.utils.tokenView.vo.txeth;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TokenTransfer
 */
@NoArgsConstructor
@Data
public class TokenTransfer {
    /**
     * index
     */
    private Integer index;
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
     * tokenInfo
     */
    private TokenInfo tokenInfo;
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
     * imageUrl
     */
    private String imageUrl;
}
