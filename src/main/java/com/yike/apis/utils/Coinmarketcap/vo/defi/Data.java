package com.yike.apis.utils.Coinmarketcap.vo.defi;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data
 */
@NoArgsConstructor
@lombok.Data
public class Data {
    /**
     * cryptoCurrencyList
     */
    private List<CryptoCurrencyList> cryptoCurrencyList;
    /**
     * totalCount
     */
    private String totalCount;
}
