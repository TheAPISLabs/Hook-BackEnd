package com.yike.apis.controller;

import cn.hutool.core.util.ObjectUtil;
import com.yike.apis.service.RoleService;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Api(description = "barRole")
@RequestMapping("/barRole")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * Obtain the permission of the user navigation bar
     * @return
     */
    @ApiOperation("Obtain the permission of the user navigation bar")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uId", value = "uId", required = false, dataType = "string")
    })
    @RequestMapping(value = "/getUserBarRole", method = RequestMethod.GET)
    public ResponseData getUserBarRole(@RequestParam(required = false) String uId){
        return roleService.getUserBarRole(uId);
    }
}
