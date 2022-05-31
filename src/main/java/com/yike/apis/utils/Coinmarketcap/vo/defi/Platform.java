package com.yike.apis.utils.Coinmarketcap.vo.defi;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Platform
 */
@NoArgsConstructor
@Data
public class Platform {
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
     * tokenAddress
     */
    private String tokenAddress;
}
