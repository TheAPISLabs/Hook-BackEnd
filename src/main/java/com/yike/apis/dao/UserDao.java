package com.yike.apis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yike.apis.pojo.role.Role;
import com.yike.apis.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
    Role getUserBarRole(@Param("valueOf") String valueOf);
}