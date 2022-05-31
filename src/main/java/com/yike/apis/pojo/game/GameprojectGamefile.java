package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * gameproject_gamefile
 * @author 
 */
@Data
@TableName(value = "gameproject_gamefile")
public class GameprojectGamefile implements Serializable {
    @TableId
    private String gpfId;

    private String gpId;

    private String gfId;

    private static final long serialVersionUID = 1L;
}