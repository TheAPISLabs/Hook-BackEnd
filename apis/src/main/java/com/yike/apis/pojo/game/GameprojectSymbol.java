package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * gameproject_symbol
 * @author 
 */
@Data
@TableName(value = "gameproject_symbol")
public class GameprojectSymbol implements Serializable {
    @TableId
    private String gpsId;

    private String gpId;

    private String name;

    private String volume;

    private String marketValue;

    private String imgUrl;

    private String tokenHash;

    private String symbol;

    private String type;

    private String status;

    private String price;

    private String circulatingSupply;

    private String species;

    private String introductionCurrency;

    private String activeUsers;

    private static final long serialVersionUID = 1L;
}