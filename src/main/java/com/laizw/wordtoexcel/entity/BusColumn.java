package com.laizw.wordtoexcel.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

/**
 * 业务字段表
 * @TableName bus_column
 */
@Data
public class BusColumn implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 表id
     */
    private String tableId;

    /**
     * 别名
     */
    private String key;

    /**
     * 名字
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 
     */
    private Integer length;

    /**
     * 
     */
    private Integer decimal;

    /**
     * 
     */
    private Integer required;

    /**
     * 
     */
    private Integer primary;

    /**
     * 序号
     */
    private Integer sn;

    /**
     * 
     */
    private String defaultValue;

    /**
     * 
     */
    private String comment;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    private static final long serialVersionUID = 1L;
}