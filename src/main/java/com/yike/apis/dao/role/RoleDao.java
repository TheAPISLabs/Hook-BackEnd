package com.yike.apis.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yike.apis.pojo.role.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao extends BaseMapper<Role> {
}