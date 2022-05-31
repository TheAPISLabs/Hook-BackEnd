package com.yike.apis.utils.Coinmarketcap.vo.nft;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data
 */
@NoArgsConstructor
@lombok.Data
public class Data {
    /**
     * count
     */
    private String count;
    /**
     * collections
     */
    private List<Collections> collections;
    /**
     * blockChains
     */
    private List<String> blockChains;
}
