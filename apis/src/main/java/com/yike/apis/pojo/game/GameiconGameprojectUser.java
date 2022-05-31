package com.yike.apis.pojo.game;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * gameicon_user
 * @author 
 */
@Data
@TableName(value = "gameicon_gameproject_user")
public class GameiconGameprojectUser implements Serializable {
    @TableId
    private String giuId;

    private String giId;

    private String uId;

    private String gpId;

    private static final long serialVersionUID = 1L;
}