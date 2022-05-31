package com.yike.apis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yike.apis.pojo.search.SearchTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchTagDao extends BaseMapper<SearchTag> {
    public List<SearchTag> selectByAddress(@Param("address") String address);
}