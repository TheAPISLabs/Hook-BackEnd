package com.yike.apis.pojo.mini;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * marketcapitalization
 * @author 
 */
@Data
@TableName(value = "marketcapitalization")
public class Marketcapitalization{
    @TableId
    private String mcId;

    /**
     * Market value
     */
    private Double marketCap;

    /**
     * Trading volume
     */
    private Double volume;

    /**
     * The number of
     */
    private Double circulatingSupply;

    /**
     * type：1 NFT，2 Defi，3  metaverse
     */
    private Integer type;

    /**
     * The time stamp
     */
    private Long time;

    private static final long serialVersionUID = 1L;
}