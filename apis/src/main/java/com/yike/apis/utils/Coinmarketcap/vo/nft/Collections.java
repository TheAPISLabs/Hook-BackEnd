package com.yike.apis.utils.Coinmarketcap.vo.nft;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Collections
 */
@NoArgsConstructor
@Data
public class Collections {
    /**
     * rank
     */
    private Integer rank;
    /**
     * slug
     */
    private String slug;
    /**
     * name
     */
    private String name;
    /**
     * floorPrice
     */
    private Integer floorPrice;
    /**
     * floorPriceUsd
     */
    private Double floorPriceUsd;
    /**
     * floorPriceToken
     */
    private String floorPriceToken;
    /**
     * popular
     */
    private Boolean popular;
    /**
     * logo
     */
    private String logo;
    /**
     * website
     */
    private String website;
    /**
     * marketCap
     */
    private Double marketCap;
    /**
     * marketCapUsd
     */
    private Double marketCapUsd;
    /**
     * owners
     */
    private Integer owners;
    /**
     * assets
     */
    private Integer assets;
    /**
     * dataSource
     */
    private String dataSource;
    /**
     * oneDay
     */
    private OneDay oneDay;
    /**
     * sevenDay
     */
    private SevenDay sevenDay;
    /**
     * thirtyDay
     */
    private ThirtyDay thirtyDay;
    /**
     * allTime
     */
    private AllTime allTime;
    /**
     * blockchain
     */
    private String blockchain;
    /**
     * ownerAssetsPercentage
     */
    private Double ownerAssetsPercentage;
    /**
     * netWorth
     */
    private Double netWorth;
    /**
     * volume7d
     */
    private Double volume7d;
    /**
     * volumeAT
     */
    private Double volumeAT;
    /**
     * sales7d
     */
    private Integer sales7d;
    /**
     * salesAT
     */
    private Integer salesAT;
    /**
     * tradersAT
     */
    private Integer tradersAT;
}
