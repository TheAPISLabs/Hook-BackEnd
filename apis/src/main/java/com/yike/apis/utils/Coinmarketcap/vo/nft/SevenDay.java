package com.yike.apis.utils.Coinmarketcap.vo.nft;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SevenDay
 */
@NoArgsConstructor
@Data
public class SevenDay {
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
     * volumeChangePercentage
     */
    private Double volumeChangePercentage;
    /**
     * sales
     */
    private Integer sales;
    /**
     * salesChangePercentage
     */
    private Double salesChangePercentage;
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
    /**
     * averagePriceChangePercentage
     */
    private Double averagePriceChangePercentage;
}
