package com.yike.apis.utils.feixiaohao.vo.holders;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * List
 */
@NoArgsConstructor
@Data
public class List {
    /**
     * updatedate
     */
    private Integer updatedate;
    /**
     * addrcount
     */
    private Integer addrcount;
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
