package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Features
 */
@NoArgsConstructor
@Data
public class Features {
    /**
     * small
     */
    private Small small;
    /**
     * large
     */
    private Large large;
    /**
     * medium
     */
    private Medium medium;
    /**
     * orig
     */
    private Orig orig;
}
