package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * gameremark
 * @author 
 */
@Data
@TableName(value = "gameremark")
public class Gameremark implements Serializable {
    @TableId
    private String grId;

    private String gpId;

    private String uId;

    private String parentId;

    private String rootParentId;

    private String content;

    private Long time;

    private Long liked;

    private static final long serialVersionUID = 1L;
}