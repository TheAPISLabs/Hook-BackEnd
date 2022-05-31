package com.yike.apis.pojo.game.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LikedCountVo implements Serializable {
    @JsonProperty("linkedGameprojectId")
    private String linkedGameprojectId;

    @JsonProperty("count")
    private Long count;

    public LikedCountVo(String linkedGameprojectId, Long count) {
        this.linkedGameprojectId = linkedGameprojectId;
        this.count = count;
    }
}
