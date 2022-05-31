package com.yike.apis.utils.reponseUtil;

/**
 * @author ：
 * @version :
 * @date ：
 * @description: Message enumeration encapsulation, custom modification
 * @modified By：
 */

public enum ResultEnums {

    SUCCESS("200", "The request is successful"),
    ERROR("500", "The request failed"),
    LOGINAUTHENTICATION("403", "Login authentication failure");
    private String code;
    private String msg;

    ResultEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
