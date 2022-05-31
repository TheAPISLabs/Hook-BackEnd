package com.yike.apis.utils.reponseUtil;

import java.io.Serializable;

/**
 * @author ：
 * @version :
 * @date ：
 * @description: Return to front-end data encapsulation
 * @modified By：
 */
public class ResponseData<T> implements Serializable {
    //Status code
    private String code;
    //alerts
    private String msg;
    //Return the data
    private T data;

    public ResponseData() {
    }

    public ResponseData(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseData(ResultEnums resultEnums) {
        this.code = resultEnums.getCode();
        this.msg = resultEnums.getMsg();
    }

    public ResponseData(ResultEnums resultEnums, T data) {
        this.code = resultEnums.getCode();
        this.msg = resultEnums.getMsg();
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
