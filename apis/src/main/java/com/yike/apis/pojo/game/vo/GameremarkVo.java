package com.yike.apis.pojo.game.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.yike.apis.pojo.game.Gameremark;
import lombok.Data;

import java.util.List;

@Data
public class GameremarkVo {
    private String grId;

    private String gpId;

    private String uId;

    private String userName;

    private Integer vip;

    private String parentUserName;

    private String userIcon;

    private String parentUserIcon;

    private String parentId;

    private String rootParentId;

    private String content;

    private Long time;

    private Boolean isLiked;

    private Long liked;

    private List<GameremarkVo> child;
}
