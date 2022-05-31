package com.yike.apis.dao.game;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yike.apis.pojo.game.GameiconGameprojectUser;
import com.yike.apis.pojo.game.vo.GameiconVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameiconGameprojectUserDao extends BaseMapper<GameiconGameprojectUser> {
    List<String> getUserIcons(@Param("giId") String giId);

    Page<GameiconVo> getGameIconByGpId(Page<GameiconVo> iPage, @Param(Constants.WRAPPER) QueryWrapper<GameiconVo> wrapper);
}