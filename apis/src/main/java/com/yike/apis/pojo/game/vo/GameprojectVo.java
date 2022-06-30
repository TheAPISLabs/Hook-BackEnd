package com.yike.apis.pojo.game.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.yike.apis.pojo.game.Gamefile;
import com.yike.apis.pojo.game.GameprojectSymbol;
import lombok.Data;

import java.util.List;

@Data
public class GameprojectVo {
    @TableId
    private String gpId;

    private String name;

    private String genres;

    private String version;

//    private String tokenHash;

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

    private List<GameprojectSymbol> top;

    private List<GameprojectSymbol> nfts;

    private List<GameprojectSymbol> defis;

    private List<Gamefile> videos;

    private List<Gamefile> imgs;

    private List<Gamefile> icon;
}
