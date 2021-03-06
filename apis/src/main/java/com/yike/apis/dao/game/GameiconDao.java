package com.yike.apis.dao.game;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yike.apis.pojo.game.Gameicon;
import com.yike.apis.pojo.game.vo.GameiconVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameiconDao extends BaseMapper<Gameicon> {

    List<GameiconVo> selectListGameiconVo();
}