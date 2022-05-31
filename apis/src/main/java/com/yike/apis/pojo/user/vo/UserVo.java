package com.yike.apis.pojo.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserVo {
    @JsonProperty("uId")
    private String uId;
    /**
     *
     */
    @JsonProperty("address")
    private String address;

    /**
     *
     */
    @JsonProperty("userName")
    private String userName;

    /**
     *
     */
    @JsonProperty("email")
    private String email;

    /**
     *
     */
    @JsonProperty("password")
    private String password;

    /**
     *
     */
    @JsonProperty("passwordNew")
    private String passwordNew;

    /**
     *
     */
    @JsonProperty("IP")
    private String IP;

    /**
     *
     */
    @JsonProperty("code")
    private String code;

    /**
     *
     */
    private String  icon;

    /**
     *
     */
    private String  twtter;
}
