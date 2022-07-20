package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * gameproject
 * @author 
 */
@Data
@TableName(value = "gameproject")
public class Gameproject implements Serializable {
    @TableId
    private String gpId;

    private String name;

    private String genres;

    private String version;

    private String gtId;

    private Long initialReleaseDate;

    private Integer twitterFollower;

//    private Integer activeUsers;

    private String gameStudio;

//    private String volume;

    private String gameIntroduction;

//    private String totalNFT;

    private String gameUrl;

//    private String imgUrl;

    private Long liked;

    private Long ngmi;

    private Long moon;

    private String twtter;

    private Integer backPeriod;

//    private String openseaName;

//    private String type;

//    private String species;

    private String gameUserIcon;

//    private String price;

//    private String circulatingSupply;

    private static final long serialVersionUID = 1L;
}