package com.yike.apis.utils.Coinmarketcap.vo.defi;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Status
 */
@NoArgsConstructor
@Data
public class Status {
    /**
     * timestamp
     */
    private String timestamp;
    /**
     * errorCode
     */
    private String errorCode;
    /**
     * errorMessage
     */
    private String errorMessage;
    /**
     * elapsed
     */
    private String elapsed;
    /**
     * creditCount
     */
    private Integer creditCount;
}
