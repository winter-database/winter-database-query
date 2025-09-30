package io.github.winter.database.query.builder;

import io.github.winter.database.query.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 字段
 *
 * @author changebooks@qq.com
 */
public interface ColumnBuilder {
    /**
     * 字段
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
     * 全字段
     *
     * @param tableNames [ 表名 ]
     * @return [ the {@link Column} instance ]
     */
    List<Column> buildAsterisk(List<String> tableNames);

    /**
     * 连接函数
     *
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @return 函数 + 表名 + 字段名
     */
    @NotEmpty
    String joinFunc(int funcType, String tableName, String columnName);

    /**
     * 解析类型
     *
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @return 类型
     */
    Class<?> parseType(int funcType, String tableName, String columnName);

}
