package com.yike.apis.utils.tokenView.vo.Websearch.txeth;

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
     * fromIsContract
     */
    private Integer fromIsContract;
    /**
     * toIsContract
     */
    private Integer toIsContract;
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
     * fromAlias
     */
    private String fromAlias;
}
