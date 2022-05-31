package com.yike.apis.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yike.apis.pojo.role.NavigationPermissions;
import com.yike.apis.pojo.role.vo.NavigationPermissionsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NavigationPermissionsDao extends BaseMapper<NavigationPermissions> {

    List<NavigationPermissionsVo> getUserBarRole(@Param("uId") String uId);

    List<NavigationPermissionsVo> getUserBarRole2(@Param("roleName") String roleName);
}