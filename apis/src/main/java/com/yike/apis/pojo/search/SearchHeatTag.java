package com.yike.apis.pojo.search;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * search_heat_tag
 * @author 
 */
@Data
@TableName(value = "search_heat_tag")
public class SearchHeatTag implements Serializable {
    @TableId
    private String shtId;

    private String shId;

    private String stId;

    /**
     *
     */
    private String address;

    private static final long serialVersionUID = 1L;
}