package com.yike.apis.pojo.role;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * user_role
 * @author 
 */
@Data
@TableName(value = "user_role")
public class UserRole implements Serializable {
    @TableId
    @JsonProperty("urId")
    private String urId;

    @JsonProperty("uId")
    private String uId;

    @JsonProperty("rId")
    private String rId;

    private static final long serialVersionUID = 1L;
}