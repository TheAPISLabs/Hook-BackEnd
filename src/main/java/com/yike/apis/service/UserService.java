package com.yike.apis.service;

import com.yike.apis.pojo.user.User;
import com.yike.apis.pojo.user.vo.UserVo;
import com.yike.apis.utils.reponseUtil.ResponseData;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    ResponseData register(UserVo userVo, HttpServletRequest request);

    ResponseData updataUser(User user);

    ResponseData updataLoginPwdByPwd(UserVo userVo);

    ResponseData updataLoginPwdByCode(UserVo userVo);

    ResponseData setAddress(UserVo userVo);

    ResponseData getUser(String email);

    ResponseData loginByPwd(UserVo userVo, HttpServletRequest request);

    ResponseData loginByCode(UserVo userVo, HttpServletRequest request);

    ResponseData logout(UserVo userVo);

    ResponseData isLogin();

    ResponseData forgotPassword(UserVo userVo);

    ResponseData resetPassword(UserVo userVo);
}
