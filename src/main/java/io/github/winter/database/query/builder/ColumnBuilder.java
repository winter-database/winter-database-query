package io.github.winter.database.query.builder;

import io.github.winter.database.query.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 创建字段
 *
 * @author changebooks@qq.com
 */
public interface ColumnBuilder {
    /**
     * 创建
     *
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @param asName     别名
     * @return the {@link Column} instance
     */
    @NotNull
    Column build(int funcType, String tableName, String columnName, String asName);

    /**
     * 连接别名
     *
     * @param fieldName 函数 + 表名 + 字段名
     * @param asName    别名
     * @return 函数 + 表名 + 字段名 + 别名
     */
    @NotNull
    String joinAs(String fieldName, String asName);

    /**
     * 连接函数
     *
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @return 函数 + 表名 + 字段名
     */
    @NotNull
    String joinFunc(int funcType, String tableName, String columnName);

    /**
     * 连接函数
     *
     * @param funcType  函数类型
     * @param fieldName 表名 + 字段名
     * @return 函数 + 表名 + 字段名
     */
    @NotNull
    String joinFunc(int funcType, @NotNull String fieldName);

    /**
     * 连接表名
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 表名 + 字段名
     */
    @NotNull
    String joinName(String tableName, String columnName);

    /**
     * 解析别名
     *
     * @param asName    自定义的别名
     * @param fieldName 函数 + 表名 + 字段名
     * @return 自定义的别名或解析的别名
     */
    @NotNull
    String asName(String asName, String fieldName);

    /**
     * 解析类型
     *
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @return 字段类型
     */
    Class<?> parseType(int funcType, String tableName, String columnName);

    /**
     * 解析类型
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 字段类型
     */
    Class<?> parseType(String tableName, String columnName);

}
