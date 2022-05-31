package com.yike.apis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yike.apis.pojo.search.SearchHeat;
import com.yike.apis.pojo.search.SearchTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchheatDao extends BaseMapper<SearchHeat> {

}