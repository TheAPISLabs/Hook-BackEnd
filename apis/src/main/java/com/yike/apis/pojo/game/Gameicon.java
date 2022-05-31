package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * gameicon
 * @author 
 */
@Data
@TableName(value = "gameicon")
public class Gameicon implements Serializable {
    @TableId
    private String giId;

    private String icon;

    private static final long serialVersionUID = 1L;
}