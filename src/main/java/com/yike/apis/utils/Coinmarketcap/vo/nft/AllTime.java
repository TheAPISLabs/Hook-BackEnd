package com.yike.apis.utils.Coinmarketcap.vo.nft;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AllTime
 */
@NoArgsConstructor
@Data
public class AllTime {
    /**
     * marketCap
     */
    private Double marketCap;
    /**
     * marketCapUsd
     */
    private Double marketCapUsd;
    /**
     * volume
     */
    private Double volume;
    /**
     * volumeUsd
     */
    private Double volumeUsd;
    /**
     * volumeChangeUsd
     */
    private Integer volumeChangeUsd;
    /**
     * sales
     */
    private Integer sales;
    /**
     * averagePrice
     */
    private Double averagePrice;
    /**
     * averagePriceUsd
     */
    private Double averagePriceUsd;
    /**
     * averagePriceChangeUsd
     */
    private Integer averagePriceChangeUsd;
}
