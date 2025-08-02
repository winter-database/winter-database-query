package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * 分组
 *
 * @author changebooks@qq.com
 */
public final class QueryGroup implements Serializable {
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

}
