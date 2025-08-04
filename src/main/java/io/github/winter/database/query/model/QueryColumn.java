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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getFunc() {
        return func;
    }

    public void setFunc(int func) {
        this.func = func;
    }

}
