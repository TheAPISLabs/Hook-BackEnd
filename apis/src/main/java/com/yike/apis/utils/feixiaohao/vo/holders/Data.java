package com.yike.apis.utils.feixiaohao.vo.holders;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data
 */
@NoArgsConstructor
@lombok.Data
public class Data {
    /**
     * top
     */
    private Top top;
    /**
     * maincoins
     */
    private List<?> maincoins;
    /**
     * toplist
     */
    private List<Toplist> toplist;
    /**
     * holders
     */
    private List<HoldersX> holders;
    /**
     * flows
     */
    private List<Flows> flows;
    /**
     * holdcoin
     */
    private Holdcoin holdcoin;
}
