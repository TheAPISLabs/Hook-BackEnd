package com.yike.apis.dao.game;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yike.apis.pojo.game.Gameremark;
import com.yike.apis.pojo.game.vo.GameremarkVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameremarkDao extends BaseMapper<Gameremark> {

    Page<GameremarkVo> getComments(Page<GameremarkVo> iPage, @Param(Constants.WRAPPER) QueryWrapper<GameremarkVo> wrapper);

    List<GameremarkVo> getCommentsByParentId(@Param("parentId") String parentId);

    List<GameremarkVo> getCommentsByRootParentId(@Param("rootParentId") String rootParentId);
}