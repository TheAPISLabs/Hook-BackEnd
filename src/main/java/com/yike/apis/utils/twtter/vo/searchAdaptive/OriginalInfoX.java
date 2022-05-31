package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * OriginalInfoX
 */
@NoArgsConstructor
@Data
public class OriginalInfoX {
    /**
     * width
     */
    private Integer width;
    /**
     * focusRects
     */
    private List<FocusRectsX> focusRects;
    /**
     * height
     */
    private Integer height;
}
