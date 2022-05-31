package com.yike.apis.pojo.role;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * interface
 * @author 
 */
@Data
@TableName(value = "interface")
public class Interface implements Serializable {
    @TableId
    @JsonProperty("iId")
    private String iId;

    private String name;

    private static final long serialVersionUID = 1L;
}