package io.github.winter.database.query.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 字段
 *
 * @author changebooks@qq.com
 */
public final class QueryColumnEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 查询主键
     */
    private Integer queryId;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 函数类型
     */
    private Integer funcType;

    /**
     * 排序
     */
    private Integer showPriority;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date lastUpdate;

}
