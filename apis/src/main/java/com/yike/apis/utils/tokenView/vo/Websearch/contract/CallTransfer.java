package com.yike.apis.utils.tokenView.vo.Websearch.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CallTransfer
 */
@NoArgsConstructor
@Data
public class CallTransfer {
    /**
     * index
     */
    private Integer index;
    /**
     * from
     */
    private String from;
    /**
     * to
     */
    private String to;
    /**
     * toAlias
     */
    private String toAlias;
    /**
     * value
     */
    private String value;
    /**
     * gasUsed
     */
    private Integer gasUsed;
    /**
     * gasLimit
     */
    private Integer gasLimit;
    /**
     * create
     */
    private String create;
}
