package com.yike.apis.dao.game;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yike.apis.pojo.game.Gamefile;
import com.yike.apis.pojo.game.GameprojectSymbol;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameprojectSymbolDao extends BaseMapper<GameprojectSymbol> {
    List<GameprojectSymbol> getGameprojectSymbolByGpId(@Param("gpId") String gpId);

    List<GameprojectSymbol> getGameprojectSymbolByGpId1(@Param("gpId") String gpId);

    List<GameprojectSymbol> getGameprojectSymbolByGpId2(@Param("gpId") String gpId);

}