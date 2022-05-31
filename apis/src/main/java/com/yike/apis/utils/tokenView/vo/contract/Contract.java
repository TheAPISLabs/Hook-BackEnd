package com.yike.apis.utils.tokenView.vo.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Contract {

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
    private com.yike.apis.utils.tokenView.vo.contract.Data data;
}
