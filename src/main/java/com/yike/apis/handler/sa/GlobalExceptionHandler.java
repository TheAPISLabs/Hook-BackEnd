package com.yike.apis.handler.sa;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import com.yike.apis.utils.reponseUtil.ResultEnums;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = NotLoginException.class)
    public ResponseData handleNotLoginException(NotLoginException e) {
        return ResponseDataUtil.buildError(ResultEnums.LOGINAUTHENTICATION.getCode(),ResultEnums.LOGINAUTHENTICATION.getMsg(),e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = NotRoleException.class)
    public ResponseData handleNotLoginException(NotRoleException e) {
        return ResponseDataUtil.buildError(ResultEnums.LOGINAUTHENTICATION.getCode(),ResultEnums.LOGINAUTHENTICATION.getMsg(),"e.getMessage()");
    }
}
