package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * gamebanner
 * @author 
 */
@Data
@TableName(value = "gamebanner")
public class Gamebanner implements Serializable {
    @TableId
    private String gbId;

    private String name;

    private String url;

    private static final long serialVersionUID = 1L;
}