package com.yike.apis.utils.tokenView.vo.charts;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Charts {

    /**
     * code
     */
    private Integer code;
    /**
     * msg
     */
    private String msg;
    /**
     * value
     */
    private String value;
    /**
     * priceUsdChangerate
     */
    private Double priceUsdChangerate;
    /**
     * priceBtcChangerate
     */
    private Double priceBtcChangerate;
    /**
     * marketcapChangerate
     */
    private Double marketcapChangerate;
    /**
     * volChangerate
     */
    private Integer volChangerate;
}
