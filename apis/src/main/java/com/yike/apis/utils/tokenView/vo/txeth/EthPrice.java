package com.yike.apis.utils.tokenView.vo.txeth;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EthPrice
 */
@NoArgsConstructor
@Data
public class EthPrice {
    /**
     * ethUsdPriceNow
     */
    private String ethUsdPriceNow;
    /**
     * ethUsdPricePast
     */
    private String ethUsdPricePast;
}
