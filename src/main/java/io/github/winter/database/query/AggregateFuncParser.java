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
     * @param name          名称
     * @return COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    String parse(int aggregateFunc, String name);

}
