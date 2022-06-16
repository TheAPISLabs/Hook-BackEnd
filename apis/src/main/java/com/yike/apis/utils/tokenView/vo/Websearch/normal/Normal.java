package com.yike.apis.utils.tokenView.vo.Websearch.normal;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Normal {

    /**
     * code
     */
    private Integer code;
    /**
     * msg
     */
    private String msg;
    /**
     * data
     */
    private List<com.yike.apis.utils.tokenView.vo.Websearch.normal.Data> data;
}
