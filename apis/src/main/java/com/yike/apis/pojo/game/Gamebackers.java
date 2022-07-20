package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * gamebackers
 * @author 
 */
@Data
@TableName(value = "gamebackers")
public class Gamebackers implements Serializable {
    private String gbid;

    private String gpid;

    private String backericon;

    private String description;

    private static final long serialVersionUID = 1L;
}