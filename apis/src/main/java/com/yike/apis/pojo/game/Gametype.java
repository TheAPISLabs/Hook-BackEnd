package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * gametype
 * @author 
 */
@Data
@TableName(value = "gametype")
public class Gametype implements Serializable {
    @TableId
    private String gtId;

    private String name;

    private static final long serialVersionUID = 1L;
}