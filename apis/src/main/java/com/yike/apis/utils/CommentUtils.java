package com.yike.apis.utils;

import cn.hutool.core.util.ObjectUtil;
import com.yike.apis.pojo.game.vo.GameremarkVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentUtils {

    public static List<GameremarkVo> processGameremarkVos(List<GameremarkVo> list, boolean useTree) {
        // Space for Time: Put GameremarkVo_id and GameremarkVo into the map to quickly retrieve GameremarkVo
        Map<String, GameremarkVo> map = new HashMap<>();
        // The list that will eventually be returned
        List<GameremarkVo> result = new ArrayList<>();
        // Iterate once to add all GameremarkVo to the map for quick lookup
        // If it's a parent comment, it goes directly to the result list
        for (GameremarkVo gameremarkVo : list) {
            if (ObjectUtil.isEmpty(gameremarkVo.getParentId()) || gameremarkVo.getParentId().equals("0")) {
                result.add(gameremarkVo);
            }
            map.put(gameremarkVo.getGrId(), gameremarkVo);
        }

        // Again, the child comment is placed in the child of the parent comment
        for (GameremarkVo gameremarkVo : list) {
            // Set the nickname and email of the parent comment for the child comment for front-end display
            if (ObjectUtil.isNotEmpty(gameremarkVo.getParentId()) && !gameremarkVo.getParentId().equals("0")) {
                GameremarkVo parentGameremarkVo = map.get(gameremarkVo.getParentId());
                gameremarkVo.setParentUserName(parentGameremarkVo.getUserName());
                gameremarkVo.setParentUserIcon(parentGameremarkVo.getParentUserIcon());
            }
            String id;
            // If the child comment is constructed in a tree structure, the id needed is parent_id
            // In other ways (a two-dimensional array), the id needed is root_parent_id
            if (useTree) {
                id = gameremarkVo.getParentId();
            } else {
                id = gameremarkVo.getRootParentId();
            }
            // As long as there is a parent comment/root parent comment
            if (ObjectUtil.isNotEmpty(id) && !id.equals("0")) {
                // Quickly get comments directly from the map and add yourself to a subset of the parent comments
                GameremarkVo p = map.get(id);
                if (p.getChild() == null) {
                    p.setChild(new ArrayList<>());
                }
                p.getChild().add(gameremarkVo);
            }
        }
        return result;
    }

}
