package com.yike.apis.utils.tokenView.vo.Maincoinex;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Maincoinex {

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
    private com.yike.apis.utils.tokenView.vo.Maincoinex.Data data;
}
