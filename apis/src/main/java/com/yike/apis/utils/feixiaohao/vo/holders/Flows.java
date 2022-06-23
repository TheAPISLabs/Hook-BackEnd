package com.yike.apis.utils.feixiaohao.vo.holders;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Flows
 */
@NoArgsConstructor
@Data
public class Flows {
    /**
     * address
     */
    private String address;
    /**
     * quantity
     */
    private Double quantity;
    /**
     * percentage
     */
    private Double percentage;
    /**
     * platform
     */
    private String platform;
    /**
     * platformName
     */
    private String platformName;
    /**
     * logo
     */
    private String logo;
    /**
     * change
     */
    private Double change;
    /**
     * blockurl
     */
    private String blockurl;
    /**
     * changeAbs
     */
    private Double changeAbs;
    /**
     * updatetime
     */
    private String updatetime;
    /**
     * hidden
     */
    private Integer hidden;
    /**
     * destroy
     */
    private Integer destroy;
    /**
     * iscontract
     */
    private Integer iscontract;
    /**
     * addressflag
     */
    private String addressflag;
}
