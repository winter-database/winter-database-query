package io.github.winter.database.query;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * 字段
 *
 * @author changebooks@qq.com
 */
public final class Column implements Serializable {
    /**
     * 函数 + 表名 + 字段名 + 别名
     */
    private String sqlName;

    /**
     * 函数 + 表名 + 字段名
     */
    private String fieldName;

    /**
     * 别名
     */
    private String asName;

    /**
     * 函数类型
     */
    private int funcType;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段类型
     */
    private Class<?> valueType;

    @NotNull
    public String getSqlName() {
        return sqlName != null ? sqlName : "";
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName != null ? sqlName.trim() : "";
    }

    @NotNull
    public String getFieldName() {
        return fieldName != null ? fieldName : "";
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName != null ? fieldName.trim() : "";
    }

    @NotNull
    public String getAsName() {
        return asName != null ? asName : "";
    }

    public void setAsName(String asName) {
        this.asName = asName != null ? asName.trim() : "";
    }

    public int getFuncType() {
        return funcType;
    }

    public void setFuncType(int funcType) {
        this.funcType = funcType;
    }

    @NotNull
    public String getTableName() {
        return tableName != null ? tableName : "";
    }

    public void setTableName(String tableName) {
        this.tableName = tableName != null ? tableName.trim() : "";
    }

    @NotNull
    public String getColumnName() {
        return columnName != null ? columnName : "";
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName != null ? columnName.trim() : "";
    }

    @NotNull
    public Class<?> getValueType() {
        return valueType != null ? valueType : Object.class;
    }

    public void setValueType(Class<?> valueType) {
        this.valueType = valueType;
    }

}
