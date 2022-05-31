package com.yike.apis.pojo.user;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
@TableName(value = "user")
public class User implements Serializable {
    @TableId
    private String uId;

    /**
     *
     */
    private String address;

    /**
     *
     */
    private String userName;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private Long lastLoginTime;

    /**
     *
     */
    private String lastLoginIp;

    /**
     *
     */
    private Integer vip;

    /**
     *
     */
    private String  icon;

    /**
     *
     */
    private String  twtter;

    private static final long serialVersionUID = 1L;
}