package com.yike.apis.utils.tokenView.vo.Websearch;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Coinlist
 */
@NoArgsConstructor
@Data
public class Coinlist {
    /**
     * coincode
     */
    private String coincode;
    /**
     * coinname
     */
    private String coinname;
    /**
     * coinlogo
     */
    private String coinlogo;
    /**
     * coinurl
     */
    private String coinurl;
    /**
     * marketValue
     */
    private Double marketValue;
    /**
     * price
     */
    private Double price;
    /**
     * changePercent
     */
    private Double changePercent;
    /**
     * status
     */
    private Integer status;
    /**
     * symbol
     */
    private String symbol;
    /**
     * volume
     */
    private Double volume;
    /**
     * circulatingsupply
     */
    private Double circulatingsupply;
    /**
     * logoWebp
     */
    private String logoWebp;
    /**
     * score
     */
    private Integer score;
    /**
     * rankNo
     */
    private Integer rankNo;
    /**
     * nativeName
     */
    private String nativeName;
    /**
     * circulationrate
     */
    private Double circulationrate;
}
