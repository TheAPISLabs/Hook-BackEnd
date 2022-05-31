package com.yike.apis.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.yike.apis.aop.NoRepeatSubmit;
import com.yike.apis.pojo.user.User;
import com.yike.apis.pojo.user.vo.UserVo;
import com.yike.apis.service.UserService;
import com.yike.apis.utils.reponseUtil.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@Api(description = "user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVo", value = "userVo[Will pass:email,password,code,userName;Choose the:address]", required = true, dataType = "UserVo")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData register(@RequestBody(required = false) UserVo userVo, HttpServletRequest request){
        return userService.register(userVo,request);
    }

    @ApiOperation("loginByPwd")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVo", value = "userVo[Will pass:email,password]", required = true, dataType = "UserVo")
    })
    @RequestMapping(value = "/loginByPwd", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData loginByPwd(@RequestBody(required = false) UserVo userVo, HttpServletRequest request){
        return userService.loginByPwd(userVo,request);
    }

    @ApiOperation("loginByCode")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVo", value = "userVo[Will pass:email,code]", required = true, dataType = "UserVo")
    })
    @RequestMapping(value = "/loginByCode", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData loginByCode(@RequestBody(required = false) UserVo userVo, HttpServletRequest request){
        return userService.loginByCode(userVo,request);
    }

    @ApiOperation("isLogin")
    @RequestMapping(value = "/isLogin", method = RequestMethod.GET)
    @SaCheckLogin
    public ResponseData isLogin(){
        return userService.isLogin();
    }

    @ApiOperation("logout")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVo", value = "userVo[Will pass:uId]", required = true, dataType = "UserVo")
    })
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @NoRepeatSubmit
    @SaCheckLogin
    public ResponseData logout(@RequestBody(required = false) UserVo userVo){
        return userService.logout(userVo);
    }

    @ApiOperation("setAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVo", value = "userVo[Will pass:uId,address]", required = true, dataType = "UserVo")
    })
    @RequestMapping(value = "/setAddress", method = RequestMethod.POST)
    @NoRepeatSubmit
    @SaCheckLogin
    public ResponseData setAddress(@RequestBody(required = false) UserVo userVo){
        return userService.setAddress(userVo);
    }

//    @ApiOperation("updataUser")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "user", value = "user[Will pass:uId;Choose the:]", required = true, dataType = "User")
//    })
//    @RequestMapping(value = "/updataUser", method = RequestMethod.POST)
//    @NoRepeatSubmit
//    @SaCheckLogin
//    public ResponseData updataUser(@RequestBody(required = false) User user){
//        return userService.updataUser(user);
//    }

//    @ApiOperation("updataLoginPwdByPwd")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userVo", value = "userVo[Will pass:uId,password,passwordNew]", required = true, dataType = "UserVo")
//    })
//    @RequestMapping(value = "/updataLoginPwdByPwd", method = RequestMethod.POST)
//    @NoRepeatSubmit
//    @SaCheckLogin
//    public ResponseData updataLoginPwdByPwd(@RequestBody(required = false) UserVo userVo){
//        return userService.updataLoginPwdByPwd(userVo);
//    }

    @ApiOperation("forgotPassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVo", value = "userVo[Will pass:email]", required = true, dataType = "UserVo")
    })
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    @NoRepeatSubmit
    @SaCheckLogin
    public ResponseData forgotPassword(@RequestBody(required = false) UserVo userVo){
        return userService.forgotPassword(userVo);
    }

    @ApiOperation("resetPassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVo", value = "userVo[Will pass:uId,password]", required = true, dataType = "UserVo")
    })
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData resetPassword(@RequestBody(required = false) UserVo userVo){
        return userService.resetPassword(userVo);
    }

//    @ApiOperation("updataLoginPwdByCode")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userVo", value = "userVo[Will pass:uId,code,passwordNew]", required = true, dataType = "UserVo")
//    })
//    @RequestMapping(value = "/updataLoginPwdByCode", method = RequestMethod.POST)
//    @NoRepeatSubmit
//    @SaCheckLogin
//    public ResponseData updataLoginPwdByCode(@RequestBody(required = false) UserVo userVo){
//        return userService.updataLoginPwdByCode(userVo);
//    }

    @ApiOperation("getUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @SaCheckLogin
    public ResponseData getUser(@RequestParam String email){
        return userService.getUser(email);
    }
}
