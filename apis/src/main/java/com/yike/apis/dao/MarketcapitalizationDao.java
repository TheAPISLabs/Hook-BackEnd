package com.yike.apis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yike.apis.pojo.mini.Marketcapitalization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MarketcapitalizationDao extends BaseMapper<Marketcapitalization> {
    public List<Marketcapitalization> getStatisticsByPeriodTime(@Param("type") Integer type, @Param("time") Long time);
}