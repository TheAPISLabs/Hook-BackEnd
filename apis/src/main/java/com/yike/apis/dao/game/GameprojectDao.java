package com.yike.apis.dao.game;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yike.apis.pojo.game.Gameproject;
import com.yike.apis.pojo.game.vo.GameprojectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameprojectDao extends BaseMapper<Gameproject> {
    Page<GameprojectVo> getGameItems(Page<GameprojectVo> iPage, @Param(Constants.WRAPPER) QueryWrapper<GameprojectVo> wrapper);
}