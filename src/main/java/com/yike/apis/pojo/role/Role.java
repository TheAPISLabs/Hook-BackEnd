package com.yike.apis.pojo.role;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * role
 * @author 
 */
@Data
@TableName(value = "role")
public class Role implements Serializable {
    @TableId
    @JsonProperty("rId")
    private String rId;

    private String roleName;

    private String power;

    private static final long serialVersionUID = 1L;
}