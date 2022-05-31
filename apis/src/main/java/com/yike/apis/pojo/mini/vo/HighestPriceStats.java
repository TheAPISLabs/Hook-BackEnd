package com.yike.apis.pojo.mini.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HighestPriceStats {
    /**
     * Market value
     */
    private BigDecimal marketCap;
    /**
     * Market float ratio
     */
    private BigDecimal marketCapRatio;
    /**
     * Trading volume
     */
    private BigDecimal volume;
    /**
     * Turnover rate
     */
    private BigDecimal volumeRatio;
    /**
     * The number of
     */
    private BigDecimal circulatingSupply;
    /**
     * Quantity fluctuation rate
     */
    private BigDecimal circulatingSupplyRatio;
}
