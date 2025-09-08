package io.github.winter.database.query;

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
     * @param func       函数
     * @param tableName  表名
     * @param columnName 字段名
     * @return COUNT(1), SUM(tableName.columnName), MAX(tableName.columnName), MIN(tableName.columnName), AVG(tableName.columnName)
     */
    String parse(int func, String tableName, String columnName);

    /**
     * 解析
     *
     * @param func 函数
     * @param name 名称
     * @return COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    String parse(int func, String name);

}
