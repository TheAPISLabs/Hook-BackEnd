package com.yike.apis.utils.tokenView.vo.tokenbalance;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TokenBalance {

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
    private List<com.yike.apis.utils.tokenView.vo.tokenbalance.Data> data;
}
