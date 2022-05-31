package com.yike.apis.utils.Coinmarketcap.vo.defi;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * CryptoCurrencyList
 */
@NoArgsConstructor
@Data
public class CryptoCurrencyList {
    /**
     * id
     */
    private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * symbol
     */
    private String symbol;
    /**
     * slug
     */
    private String slug;
    /**
     * tags
     */
    private List<String> tags;
    /**
     * cmcRank
     */
    private Integer cmcRank;
    /**
     * marketPairCount
     */
    private Integer marketPairCount;
    /**
     * circulatingSupply
     */
    private Double circulatingSupply;
    /**
     * selfReportedCirculatingSupply
     */
    private Double selfReportedCirculatingSupply;
    /**
     * totalSupply
     */
    private Double totalSupply;
    /**
     * maxSupply
     */
    private Double maxSupply;
    /**
     * ath
     */
    private Double ath;
    /**
     * atl
     */
    private Double atl;
    /**
     * high24h
     */
    private Double high24h;
    /**
     * low24h
     */
    private Double low24h;
    /**
     * isActive
     */
    private Integer isActive;
    /**
     * lastUpdated
     */
    private String lastUpdated;
    /**
     * dateAdded
     */
    private String dateAdded;
    /**
     * quotes
     */
    private List<Quotes> quotes;
    /**
     * platform
     */
    private Platform platform;
    /**
     * isAudited
     */
    private Boolean isAudited;
    /**
     * auditInfoList
     */
    private List<AuditInfoList> auditInfoList;
}
