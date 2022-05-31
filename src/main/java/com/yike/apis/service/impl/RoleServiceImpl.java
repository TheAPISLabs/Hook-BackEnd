package com.yike.apis.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.yike.apis.dao.UserDao;
import com.yike.apis.dao.role.NavigationPermissionsDao;
import com.yike.apis.pojo.role.vo.NavigationPermissionsVo;
import com.yike.apis.pojo.user.User;
import com.yike.apis.service.RoleService;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import com.yike.apis.utils.reponseUtil.ResultEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private NavigationPermissionsDao navigationPermissionsDao;

    @Override
    public ResponseData getUserBarRole(String uId) {
        List<NavigationPermissionsVo> navigationPermissionsList = null;
        if(ObjectUtil.isEmpty(uId)){
            String roleName = "normalUser";
            navigationPermissionsList = navigationPermissionsDao.getUserBarRole2(roleName);
        }else {
            User user = userDao.selectById(uId);
            if(ObjectUtil.isNotEmpty(user)){
                return ResponseDataUtil.buildError("User does not exist");
            }
            navigationPermissionsList = navigationPermissionsDao.getUserBarRole(uId);
        }
        List<NavigationPermissionsVo> vos = new ArrayList<>();
        if(ObjectUtil.isNotEmpty(navigationPermissionsList)){
            vos = buildTree(navigationPermissionsList);
        }
        return ResponseDataUtil.buildSuccess(ResultEnums.SUCCESS.getCode(),ResultEnums.SUCCESS.getMsg(),vos);
    }

    public static List<NavigationPermissionsVo> buildTree(List<NavigationPermissionsVo> categoryInfos) {
        List<NavigationPermissionsVo> collect = categoryInfos.stream().filter((data) ->
            ObjectUtil.isNotEmpty(data.getParentId()) && (data.getParentId().equals("0") || data.getParentId().equals(0) || data.getParentId() == "0")).collect(Collectors.toList());
        collect.forEach((data) -> {
            buildTree(data, categoryInfos);
        });
        return collect;
    }

    private static void buildTree(NavigationPermissionsVo vo, List<NavigationPermissionsVo> newlist) {
        String id = vo.getNpId();
        if (StrUtil.isEmpty(id)) {

        } else {
            List<NavigationPermissionsVo> subList = newlist.stream().filter((target) -> ObjectUtil.isNotEmpty(target.getParentId()) && id.equals(target.getParentId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(subList)) {
                subList.forEach((subData) -> {
                    buildTree(subData, newlist);
                });
                vo.setChildrens(subList);
            }

        }
    }
}
