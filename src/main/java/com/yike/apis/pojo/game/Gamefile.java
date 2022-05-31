package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * gamefile
 * @author 
 */
@Data
@TableName(value = "gamefile")
public class Gamefile implements Serializable {
    @TableId
    private String gfId;

    private String url;

    private Integer type;

    private String name;

    private static final long serialVersionUID = 1L;
}