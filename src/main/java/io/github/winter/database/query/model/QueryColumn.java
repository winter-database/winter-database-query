package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * 字段
 *
 * @author changebooks@qq.com
 */
public final class QueryColumn implements Serializable {
    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private Class<?> type;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 函数
     */
    private int func;

}
