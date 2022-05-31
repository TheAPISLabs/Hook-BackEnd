package com.yike.apis.dao.game;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yike.apis.pojo.game.Gamefile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GamefileDao extends BaseMapper<Gamefile> {
    List<Gamefile> getGamefileByGpId(@Param("gpId") String gpId);

    List<Gamefile> getGamefileByGpId1(@Param("gpId") String gpId);

    List<Gamefile> getGamefileByGpId2(@Param("gpId") String gpId);
}