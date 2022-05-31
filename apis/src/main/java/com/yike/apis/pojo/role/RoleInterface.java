package com.yike.apis.pojo.role;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * role_interface
 * @author 
 */
@Data
@TableName(value = "role_interface")
public class RoleInterface implements Serializable {
    @TableId
    @JsonProperty("riId")
    private String riId;

    @JsonProperty("iId")
    private String iId;

    @JsonProperty("rId")
    private String rId;

    private static final long serialVersionUID = 1L;
}