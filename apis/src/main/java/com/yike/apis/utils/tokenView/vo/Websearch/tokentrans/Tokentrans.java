package com.yike.apis.utils.tokenView.vo.Websearch.tokentrans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Tokentrans {

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
    private List<com.yike.apis.utils.tokenView.vo.Websearch.tokentrans.Data> data;
}
