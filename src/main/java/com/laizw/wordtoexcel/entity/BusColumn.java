package com.laizw.wordtoexcel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务字段表
 * @TableName bus_column
 */
@Data
@TableName(value = "bus_column")
public class BusColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_")
    private String id;

    /**
     * 表id
     */
    @TableField(value = "table_id_")
    private String tableId;

    /**
     * 别名
     */
    @TableField(value = "key_")
    private String key;

    /**
     * 名字
     */
    @TableField(value = "name_")
    private String name;

    /**
     * 类型
     */
    @TableField(value = "type_")
    private String type;
    /**
     * 字段控件
     */
    @TableField(value = "length_")
    private Integer length;

    @TableField(value = "decimal_")
    private Integer decimal;

    @TableField(value = "required_")
    private Integer required;

    @TableField(value = "primary_")
    private Integer primary;

    /**
     * 序号
     */
    @TableField(value = "sn_")
    private Integer sn;

    @TableField(value = "default_value_")
    private String defaultValue;

    @TableField(value = "comment_")
    private String comment;

    /**
     * 创建时间
     */
    @TableField(value = "create_time_")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by_")
    private String createBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time_")
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by_")
    private String updateBy;

}