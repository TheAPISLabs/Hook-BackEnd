package com.yike.apis.utils.tokenView.vo.token;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DataX
 */
@NoArgsConstructor
@lombok.Data
public class DataX {
    /**
     * code
     */
    private Integer code;
    /**
     * data
     */
    private List<Data> data;
}
