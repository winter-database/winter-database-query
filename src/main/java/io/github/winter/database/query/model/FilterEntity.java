package io.github.winter.database.query.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 条件
 *
 * @author changebooks@qq.com
 */
public final class FilterEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 查询主键
     */
    private Integer queryId;

    /**
     * 父条件主键
     */
    private Integer parentId;

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
     * 条件类型
     */
    private Integer filterType;

    /**
     * 逻辑与或
     */
    private Integer logicalOperator;

    /**
     * 分组条件？
     */
    private Boolean isHaving;

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
