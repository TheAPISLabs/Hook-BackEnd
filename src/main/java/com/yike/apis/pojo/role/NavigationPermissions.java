package com.yike.apis.pojo.role;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * navigation_permissions
 * @author 
 */
@Data
@TableName(value = "navigation_permissions")
public class NavigationPermissions implements Serializable {
    @TableId
    @JsonProperty("npId")
    private String npId;

    private String name;

    @JsonProperty("parentId")
    private String parentId;

    private String layout;

    private String path;

    private String icon;

    private String collapse;

    private String component;

    private static final long serialVersionUID = 1L;
}