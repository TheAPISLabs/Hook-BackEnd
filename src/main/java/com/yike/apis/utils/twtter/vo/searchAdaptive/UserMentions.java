package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * UserMentions
 */
@NoArgsConstructor
@Data
public class UserMentions {
    /**
     * indices
     */
    private List<Integer> indices;
    /**
     * screenName
     */
    private String screenName;
    /**
     * idStr
     */
    private String idStr;
    /**
     * name
     */
    private String name;
    /**
     * id
     */
    private Long id;
}
