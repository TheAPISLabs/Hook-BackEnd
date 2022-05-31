package com.yike.apis.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yike.apis.pojo.role.Interface;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InterfaceDao extends BaseMapper<Interface> {

    List<Interface> getInterfacesByRId(@Param("rId") String rId);

    List<Interface> getInterfacesBy(@Param("roleName") String roleName);
}