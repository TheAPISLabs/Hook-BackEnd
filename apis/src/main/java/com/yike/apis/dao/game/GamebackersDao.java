package com.yike.apis.dao.game;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yike.apis.pojo.game.Gamebackers;
import com.yike.apis.pojo.game.Gamefile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GamebackersDao extends BaseMapper<Gamebackers> {
    List<Gamebackers> getGamebackersByGpId(@Param("gpId") String gpId);
}