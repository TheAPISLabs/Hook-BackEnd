package com.yike.apis.pojo.game.vo;


import lombok.Getter;

/**
 * 用户点赞的状态
 */
@Getter
public enum LikedStatusEnum {
    LIKE(1, "give a like"),
    UNLIKE(0, "Cancel the thumb up/Not thumb up"),
    POOR(-1, "Bad review"),
    UNPOOR(0, "Cancel the bad review");

    private Integer code;

    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}