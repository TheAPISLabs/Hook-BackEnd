package com.yike.apis.pojo.game.vo;

import lombok.Data;

import java.util.List;

@Data
public class GameiconVo {
    private String giId;

    private String icon;

    private Boolean isLiked = false;

    private List<UserVo> userIcons;
}
