package com.yike.apis.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.yike.apis.dao.role.InterfaceDao;
import com.yike.apis.dao.role.RoleDao;
import com.yike.apis.pojo.role.Interface;
import com.yike.apis.pojo.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private InterfaceDao interfaceDao;
    // Register an interceptor for sa-token
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        List<Role> roles = roleDao.selectList(null);
//        Map<String,List<String>> map = new HashMap<>();
//        if(ObjectUtil.isNotEmpty(roles)){
//            for(Role role:roles){
//                List<Interface> spmsinterfaces = interfaceDao.getInterfacesByRId(role.getRId());
//                List<String> stringList = spmsinterfaces.stream().map(e -> e.getName()).collect(Collectors.toList());
//                if(ObjectUtil.isNotEmpty(stringList)){
//                    map.put(role.getRoleName(),stringList);
//                }
//            }
//        }
//
//        List<Interface> spmsinterfaces = interfaceDao.getInterfacesBy("LOGIN");
//        List<String> stringList = new ArrayList<>();
//        if(ObjectUtil.isNotEmpty(spmsinterfaces)){
//            stringList = spmsinterfaces.stream().map(e -> e.getName()).collect(Collectors.toList());
//        }
//
//
//        // Register route interceptors and customize authentication rules
//        List<String> finalList = stringList;
//        registry.addInterceptor(new SaRouteInterceptor((req, res, handler) -> {
//
//            // Login authentication - intercepts/V routes and excludes /user/doLogin for open logins
//            if(ObjectUtil.isNotEmpty(finalList) && finalList.size() > 0){
//                SaRouter.match(finalList)
//                        .check(r -> StpUtil.checkLogin());
//            }
//
//            // Role authentication: Intercept routes starting with admin. Only the admin or super-admin role can pass authentication
//            if(ObjectUtil.isNotEmpty(map) && map.keySet().size() > 0){
//                Set<String> set = map.keySet();
//                for(String s:set){
//                    SaRouter.match(map.get(s))
//                            .check(r -> StpUtil.checkRoleOr(s));
//                }
//            }
//        })).addPathPatterns("/**");
    }
}
