package com.yike.apis.utils.tokenView.vo.Websearch.tokenTokentrans;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@lombok.Data
public class TokenTokentrans {

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
    private List<Data> data;
}
