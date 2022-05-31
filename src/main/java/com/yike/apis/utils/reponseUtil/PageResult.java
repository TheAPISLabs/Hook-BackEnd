package com.yike.apis.utils.reponseUtil;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：
 * @version :
 * @date ：
 * @description: Paging data encapsulation
 * @modified By：
 */
public class PageResult<T> implements Serializable {
    private List<T> rows;    //Data per page
    private Long total;        //The total number of records
    private Integer currentPage;    //Current page count (starting from 1)
    private Integer pages;        //A total number of pages

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}