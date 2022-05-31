package com.yike.apis.controller;

import com.yike.apis.aop.NoRepeatSubmit;
import com.yike.apis.pojo.mini.vo.SearchVo;
import com.yike.apis.pojo.search.SearchTag;
import com.yike.apis.service.SearchService;
import com.yike.apis.utils.reponseUtil.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(description = "search")
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @ApiOperation("setSearchHeat")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchVo", value = "searchVo[Will pass:searchName]", required = true, dataType = "SearchVo")
    })
    @RequestMapping(value = "/setSearchHeat", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData setSearchHeat(@RequestBody SearchVo searchVo){
        return searchService.setSearchHeat(searchVo);
    }

    @ApiOperation("getSearchHeat")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "limit", required = true, dataType = "int")
    })
    @RequestMapping(value = "/getSearchHeat", method = RequestMethod.GET)
    @NoRepeatSubmit
    public ResponseData getSearchHeat(@RequestParam Integer limit){
        return searchService.getSearchHeat(limit);
    }

    @ApiOperation("getTag")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "address", required = false, dataType = "string")
    })
    @RequestMapping(value = "/getTag", method = RequestMethod.GET)
    @NoRepeatSubmit
    public ResponseData getTag(@RequestParam(required = false) String address){
        return searchService.getTag(address);
    }

    @ApiOperation("setTagAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "address", required = true, dataType = "string"),
            @ApiImplicitParam(name = "tagList", value = "tagList", required = true, dataType = "list")
    })
    @RequestMapping(value = "/setTagAddress/{address}", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData setTag_address(@PathVariable("address") String address,@RequestBody List<SearchTag> tagList){
        return searchService.setTag_address(address,tagList);
    }
}
