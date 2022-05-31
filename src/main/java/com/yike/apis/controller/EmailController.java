package com.yike.apis.controller;

import cn.hutool.core.util.ObjectUtil;
import com.yike.apis.aop.NoRepeatSubmit;
import com.yike.apis.service.EmailService;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@Api(description = "email")
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    /**
     * Send verification code
     * @return
     */
    @ApiOperation("Send verification code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "email(Will pass)", required = false, dataType = "string")
    })
    @RequestMapping(value = "/setEmailCode", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData setEmailCode(@RequestBody Map<String,String> map){
        if(ObjectUtil.isEmpty(map)){
            return ResponseDataUtil.buildError();
        }
        if(ObjectUtil.isEmpty(map.get("email"))){
            return ResponseDataUtil.buildError();
        }
        return emailService.setEmailCode(map.get("email"));
    }

    /**
     * Verification code
     * @return
     */
    @ApiOperation("Verification code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "email(Will pass)", required = false, dataType = "string"),
            @ApiImplicitParam(name = "code", value = "code(Will pass)", required = false, dataType = "string"),
    })
    @RequestMapping(value = "/verifyEmailCode", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData verifyEmailCode(@RequestBody Map<String,String> map){
        if(ObjectUtil.isEmpty(map)){
            return ResponseDataUtil.buildError();
        }
        if(ObjectUtil.isEmpty(map.get("email"))){
            return ResponseDataUtil.buildError();
        }
        if(ObjectUtil.isEmpty(map.get("code"))){
            return ResponseDataUtil.buildError();
        }
        return emailService.verifyEmailCode(map.get("email"),map.get("code"));
    }

}
