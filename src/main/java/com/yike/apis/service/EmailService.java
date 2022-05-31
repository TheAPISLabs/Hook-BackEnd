package com.yike.apis.service;

import com.yike.apis.utils.reponseUtil.ResponseData;

public interface EmailService {
    ResponseData setEmailCode(String email);

    ResponseData verifyEmailCode(String email, String code);

    public Integer verifyCode(String email, String code);
}
