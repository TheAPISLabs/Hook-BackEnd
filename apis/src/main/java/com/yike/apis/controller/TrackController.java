package com.yike.apis.controller;

import com.yike.apis.aop.NoRepeatSubmit;
import com.yike.apis.service.SearchService;
import com.yike.apis.service.TrachService;
import com.yike.apis.utils.reponseUtil.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(description = "track")
@RequestMapping("/track")
public class TrackController {
    @Autowired
    private TrachService trachService;

    @ApiOperation("tokenbalance")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "address", required = true, dataType = "string")
    })
    @RequestMapping(value = "/tokenbalance", method = RequestMethod.GET)
    @NoRepeatSubmit
    public ResponseData tokenbalance(@RequestParam String address){
        return trachService.tokenbalance(address);
    }

    @ApiOperation("tokentrans")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "address", required = true, dataType = "string"),
            @ApiImplicitParam(name = "tokenAddress", value = "tokenAddress", required = true, dataType = "string"),
            @ApiImplicitParam(name = "start", value = "start", required = true, dataType = "string"),
            @ApiImplicitParam(name = "limit", value = "limit", required = true, dataType = "string")
    })
    @RequestMapping(value = "/tokentrans", method = RequestMethod.GET)
    @NoRepeatSubmit
    public ResponseData tokentrans(@RequestParam String address,@RequestParam String tokenAddress,@RequestParam String start,@RequestParam String limit){
        return trachService.tokentrans(address,tokenAddress,start,limit);
    }

    @ApiOperation("getTag")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "address", required = true, dataType = "string")
    })
    @RequestMapping(value = "/getTag", method = RequestMethod.GET)
    @NoRepeatSubmit
    public ResponseData getTag(@RequestParam String address){
        return trachService.getTag(address);
    }

    @ApiOperation("maincoinexchange")
    @RequestMapping(value = "/maincoinexchange", method = RequestMethod.GET)
    @NoRepeatSubmit
    public Object maincoinexchange(){
        return trachService.maincoinexchange();
    }

    @ApiOperation("normal")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "address", required = true, dataType = "string"),
            @ApiImplicitParam(name = "start", value = "start", required = true, dataType = "string"),
            @ApiImplicitParam(name = "limit", value = "limit", required = true, dataType = "string")
    })
    @RequestMapping(value = "/normal", method = RequestMethod.GET)
    @NoRepeatSubmit
    public Object normal(@RequestParam String address,@RequestParam String start,@RequestParam String limit){
        return trachService.normal(address,start,limit);
    }
}
