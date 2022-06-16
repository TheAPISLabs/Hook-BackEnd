package com.yike.apis.controller;

import cn.hutool.core.util.ObjectUtil;
import com.yike.apis.aop.NoRepeatSubmit;
import com.yike.apis.service.PosterService;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.Map;

@Slf4j
@RestController
@Api(description = "poster")
@RequestMapping("/poster")
public class PosterController {
    @Autowired
    private PosterService posterService;

    /**
     * Send verification code
     * @return
     */
    @ApiOperation("Get Poster")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "poster", required = true, dataType = "string")
    })
    @RequestMapping(value = "/getPoster", method = RequestMethod.GET)
    @NoRepeatSubmit
    public ResponseData getPoster(@RequestParam String address){

        return posterService.getPoster(address);
    }
}
