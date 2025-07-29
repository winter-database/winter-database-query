package io.github.winter.database.query;

/**
 * 解析聚合函数
 *
 * @author changebooks@qq.com
 */
public interface AggregateFuncParser {
    /**
     * 解析
     *
     * @param aggregateFunc 聚合函数
     * @param tableName     表名
     * @param columnName    字段名
     * @return COUNT(1), SUM(table.column), MAX(table.column), MIN(table.column), AVG(table.column)
     */
    String parse(int aggregateFunc, String tableName, String columnName);

    /**
     * 解析
     *
     * @param aggregateFunc 聚合函数
     * @param name          名称
     * @return COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    String parse(int aggregateFunc, String name);

    /**
     * 连接名称
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 表名.字段名
     */
    String joinName(String tableName, String columnName);

}
