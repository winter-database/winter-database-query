package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * 字段
 *
 * @author changebooks@qq.com
 */
public final class QueryColumn implements Serializable {
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
