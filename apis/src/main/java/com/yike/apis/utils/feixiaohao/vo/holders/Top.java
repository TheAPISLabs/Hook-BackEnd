package com.yike.apis.utils.feixiaohao.vo.holders;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Top
 */
@NoArgsConstructor
@Data
public class Top {
    /**
     * updatedate
     */
    private String updatedate;
    /**
     * addrcount
     */
    private Long addrcount;
    /**
     * top10rate
     */
    private Double top10rate;
    /**
     * top20rate
     */
    private Double top20rate;
    /**
     * top50rate
     */
    private Double top50rate;
    /**
     * top100rate
     */
    private Double top100rate;
}
