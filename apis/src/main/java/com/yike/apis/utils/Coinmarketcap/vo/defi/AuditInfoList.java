package com.yike.apis.utils.Coinmarketcap.vo.defi;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuditInfoList
 */
@NoArgsConstructor
@Data
public class AuditInfoList {
    /**
     * coinId
     */
    private String coinId;
    /**
     * auditor
     */
    private String auditor;
    /**
     * auditStatus
     */
    private Integer auditStatus;
    /**
     * score
     */
    private String score;
    /**
     * auditTime
     */
    private String auditTime;
    /**
     * reportUrl
     */
    private String reportUrl;
    /**
     * contractAddress
     */
    private String contractAddress;
    /**
     * contractPlatform
     */
    private String contractPlatform;
}
