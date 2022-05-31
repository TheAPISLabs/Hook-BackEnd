package com.yike.apis.utils.Coinmarketcap.vo.defi;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Quotes
 */
@NoArgsConstructor
@Data
public class Quotes {
    /**
     * name
     */
    private String name;
    /**
     * price
     */
    private Double price;
    /**
     * volume24h
     */
    private Double volume24h;
    /**
     * volume7d
     */
    private Double volume7d;
    /**
     * volume30d
     */
    private Double volume30d;
    /**
     * marketCap
     */
    private Double marketCap;
    /**
     * selfReportedMarketCap
     */
    private Double selfReportedMarketCap;
    /**
     * percentChange1h
     */
    private Double percentChange1h;
    /**
     * percentChange24h
     */
    private Double percentChange24h;
    /**
     * percentChange7d
     */
    private Double percentChange7d;
    /**
     * lastUpdated
     */
    private String lastUpdated;
    /**
     * percentChange30d
     */
    private Double percentChange30d;
    /**
     * percentChange60d
     */
    private Double percentChange60d;
    /**
     * percentChange90d
     */
    private Double percentChange90d;
    /**
     * fullyDilluttedMarketCap
     */
    private Double fullyDilluttedMarketCap;
    /**
     * marketCapByTotalSupply
     */
    private Double marketCapByTotalSupply;
    /**
     * dominance
     */
    private Double dominance;
    /**
     * turnover
     */
    private Double turnover;
    /**
     * ytdPriceChangePercentage
     */
    private Integer ytdPriceChangePercentage;
}
