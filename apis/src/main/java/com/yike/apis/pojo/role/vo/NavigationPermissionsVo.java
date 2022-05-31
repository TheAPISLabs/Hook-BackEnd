package com.yike.apis.pojo.role.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NavigationPermissionsVo {
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

    @JsonProperty("childrens")
    private List<NavigationPermissionsVo> childrens;
}
