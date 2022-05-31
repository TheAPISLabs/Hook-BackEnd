package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * gameprojectlinked
 * @author 
 */
@Data
@TableName(value = "gameprojectlinked")
@AllArgsConstructor
@NoArgsConstructor
public class Gameprojectlinked implements Serializable {
    @TableId
    @JsonProperty("gplId")
    private String gplId;

    @JsonProperty("linkedGameprojectId")
    private String linkedGameprojectId;

    /**
     * 点赞的用户id
     */
    @JsonProperty("linkedUserId")
    private String linkedUserId;

    /**
     * 点赞状态，0取消，1点赞,-1差评
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonProperty("createTime")
    private Long createTime;

    /**
     * 修改时间
     */
    @JsonProperty("updateTime")
    private Long updateTime;


    public Gameprojectlinked(String linkedGameprojectId, String linkedUserId, Integer status) {
        this.linkedGameprojectId = linkedGameprojectId;
        this.linkedUserId = linkedUserId;
        this.status = status;
    }

    private static final long serialVersionUID = 1L;
}