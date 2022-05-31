package com.yike.apis.pojo.role;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * user_navigation_permissions
 * @author 
 */
@Data
@TableName(value = "user_navigation_permissions")
public class RoleNavigationPermissions implements Serializable {
    @TableId
    @JsonProperty("unpId")
    private String rnpId;

    @JsonProperty("rId")
    private String rId;

    @JsonProperty("npId")
    private String npId;

    private static final long serialVersionUID = 1L;
}