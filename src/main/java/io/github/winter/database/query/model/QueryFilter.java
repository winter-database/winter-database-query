package io.github.winter.database.query.model;

import java.io.Serializable;
import java.util.List;

/**
 * 条件
 *
 * @author changebooks@qq.com
 */
public class QueryFilter implements Serializable {
    /**
     * 条件列表
     */
    private List<QueryFilter> filters;

    /**
     * 逻辑或？
     */
    private Boolean or;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 聚合函数
     */
    private int aggregateFunc;

    /**
     * 类型
     */
    private Class<?> type;

}
