package com.yike.apis.utils.reponseUtil;

/**
 * @author ：
 * @version :
 * @date ：
 * @description: Returns data utility class encapsulation
 * @modified By：
 */
public class ResponseDataUtil {

    /**
     * Unified return with entity
     *
     * @param data entity
     * @param <T>  Entity type
     * @return
     */

    public static <T> ResponseData buildSuccess(T data) {

        return new ResponseData<T>(ResultEnums.SUCCESS, data);
    }

    /**
     * Return without data
     *
     * @return
     */
    public static ResponseData buildSuccess() {
        return new ResponseData(ResultEnums.SUCCESS);
    }

    /**
     * The success return of the self-set message
     *
     * @param msg
     * @return
     */
    public static ResponseData buildSuccess(String msg) {
        return new ResponseData(ResultEnums.SUCCESS.getCode(), msg);
    }

    /**
     * Self-setting message and status code return without data
     *
     * @param code
     * @param msg
     * @return
     */
    public static ResponseData buildSuccess(String code, String msg) {
        return new ResponseData(code, msg);
    }

    /**
     * Self set request success with message, status code, data return
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseData buildSuccess(String code, String msg, T data) {
        return new ResponseData<T>(code, msg, data);
    }

    /**
     * Returns the structure enumeration class
     *
     * @param resultEnums
     * @return
     */
    public static ResponseData buildSuccess(ResultEnums resultEnums) {
        return new ResponseData(resultEnums);
    }

    /**
     * Return request error message tape data
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseData buildError(T data) {
        return new ResponseData<T>(ResultEnums.ERROR, data);
    }

    /**
     * Return request Request error message with no data
     *
     * @return
     */
    public static ResponseData buildError() {
        return new ResponseData(ResultEnums.ERROR);
    }

    /**
     * Custom Settings return an error message
     *
     * @param msg
     * @return
     */
    public static ResponseData buildError(String msg) {
        return new ResponseData(ResultEnums.ERROR.getCode(), msg);
    }

    /**
     * Returns a custom error status code and message
     *
     * @param code
     * @param msg
     * @return
     */
    public static ResponseData buildError(String code, String msg) {
        return new ResponseData(code, msg);
    }

    /**
     * Self - set request error with message, status code, data returned
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */

    public static <T> ResponseData buildError(String code, String msg, T data) {
        return new ResponseData<T>(code, msg, data);
    }

    /**
     * Returns an error message for an enumeration class definition
     *
     * @param resultEnums
     * @return
     */
    public static ResponseData buildError(ResultEnums resultEnums) {
        return new ResponseData(resultEnums);
    }
}
