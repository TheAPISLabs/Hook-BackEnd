package com.yike.apis.pojo.search;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * searchheat
 * @author 
 */
@Data
@TableName(value = "searchheat")
public class SearchHeat implements Serializable {
    @TableId
    private String shId;

    /**
     *
     */
    private String searchName;

    /**
     *
     */
    private Long heat;

    /**
     *
     */
    private Long startTime;

    /**
     *
     */
    private Long lastTime;

    /**
     *
     */
    private Long blockno;

    private static final long serialVersionUID = 1L;
}