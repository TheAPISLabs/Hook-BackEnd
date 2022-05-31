package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Hashtags
 */
@NoArgsConstructor
@Data
public class Hashtags {
    /**
     * indices
     */
    private List<Integer> indices;
    /**
     * text
     */
    private String text;
}
