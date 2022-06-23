package com.yike.apis.utils.tokenView.vo.address;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Address {

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
    private com.yike.apis.utils.tokenView.vo.address.Data data;
}
