package io.github.winter.database.query;

import jakarta.validation.constraints.NotNull;

/**
 * 解析函数
 *
 * @author changebooks@qq.com
 */
public interface FuncParser {
    /**
     * 总行数
     */
    String COUNT = "COUNT(*)";

    /**
     * 解析
     *
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @return COUNT(1), SUM(tableName.columnName), MAX(tableName.columnName), MIN(tableName.columnName), AVG(tableName.columnName)
     */
    @NotNull
    String parse(int funcType, String tableName, String columnName);

    /**
     * 解析
     *
     * @param funcType 函数类型
     * @param name     名称
     * @return COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    @NotNull
    String parse(int funcType, @NotNull String name);

}
