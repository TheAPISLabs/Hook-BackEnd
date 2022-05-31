package com.yike.apis.utils.tokenView.vo.token;

import lombok.NoArgsConstructor;

/**
 * Data
 */
@NoArgsConstructor
@lombok.Data
public class Data {
    /**
     * description
     */
    private String description;
    /**
     * descriptionCN
     */
    private String descriptionCN;
    /**
     * holders
     */
    private Integer holders;
    /**
     * logourl
     */
    private String logourl;
    /**
     * marketcap
     */
    private Double marketcap;
    /**
     * price
     */
    private Double price;
    /**
     * tokenaddress
     */
    private String tokenaddress;
    /**
     * tokenname
     */
    private String tokenname;
    /**
     * type
     */
    private String type;
    /**
     * volume24
     */
    private Double volume24;
}
