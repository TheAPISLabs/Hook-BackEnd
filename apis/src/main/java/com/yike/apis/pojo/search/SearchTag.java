package com.yike.apis.pojo.search;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * searchtag
 * @author 
 */
@Data
@TableName(value = "searchtag")
public class SearchTag implements Serializable {
    @TableId
    private String stId;

    /**
     *
     */
    private String tagName;

    private static final long serialVersionUID = 1L;
}