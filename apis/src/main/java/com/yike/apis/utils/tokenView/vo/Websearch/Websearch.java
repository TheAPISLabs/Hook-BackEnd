package com.yike.apis.utils.tokenView.vo.Websearch;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Websearch {

    /**
     * status
     */
    private String status;
    /**
     * code
     */
    private Integer code;
    /**
     * msg
     */
    private String msg;
    /**
     * keyword
     */
    private String keyword;
    /**
     * coinCurpage
     */
    private Integer coinCurpage;
    /**
     * exchangeCurpage
     */
    private Integer exchangeCurpage;
    /**
     * coinTotalsize
     */
    private Integer coinTotalsize;
    /**
     * exchangeTotalsize
     */
    private Integer exchangeTotalsize;
    /**
     * coinMaxpage
     */
    private Integer coinMaxpage;
    /**
     * exchangeMaxpage
     */
    private Integer exchangeMaxpage;
    /**
     * walletCurpage
     */
    private Integer walletCurpage;
    /**
     * walletTotalsize
     */
    private Integer walletTotalsize;
    /**
     * walletMaxpage
     */
    private Integer walletMaxpage;
    /**
     * coinlist
     */
    private List<Coinlist> coinlist;
    /**
     * exchangelist
     */
    private List<?> exchangelist;
    /**
     * walletlist
     */
    private List<?> walletlist;
}
