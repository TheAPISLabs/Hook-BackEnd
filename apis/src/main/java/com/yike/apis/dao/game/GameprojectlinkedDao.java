package com.yike.apis.dao.game;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yike.apis.pojo.game.Gamefile;
import com.yike.apis.pojo.game.Gameprojectlinked;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GameprojectlinkedDao extends BaseMapper<Gameprojectlinked> {
    Page<Gameprojectlinked> getLikedListByLiked(Page<Gameprojectlinked> iPage, @Param(Constants.WRAPPER) QueryWrapper<Gameprojectlinked> wrapper);
}