package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * OriginalInfo
 */
@NoArgsConstructor
@Data
public class OriginalInfo {
    /**
     * width
     */
    private Integer width;
    /**
     * focusRects
     */
    private List<FocusRects> focusRects;
    /**
     * height
     */
    private Integer height;
}
