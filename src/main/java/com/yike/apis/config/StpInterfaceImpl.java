package com.yike.apis.config;

import cn.dev33.satoken.stp.StpInterface;
import com.yike.apis.dao.UserDao;
import com.yike.apis.pojo.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    private UserDao userDao;
    @Override
    public List<String> getPermissionList(Object uId, String type) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object uId, String type) {
        Role role = userDao.getUserBarRole(String.valueOf(uId));
        return Collections.singletonList(role.getRoleName());
    }
}
