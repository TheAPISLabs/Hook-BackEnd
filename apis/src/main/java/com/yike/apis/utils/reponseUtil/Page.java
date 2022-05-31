package com.yike.apis.utils.reponseUtil;

import java.io.Serializable;

/**
 * @author ：lp
 * @version :
 * @date ：2020/5/27 16:41
 * @description: Paging object
 * @modified By：
 */
public class Page implements Serializable {
    /**
     * The current number of pages
     */
    private int pageNum;
    /**
     * The number of records required per page
     */
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
