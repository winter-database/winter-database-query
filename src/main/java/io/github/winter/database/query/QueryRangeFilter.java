package io.github.winter.database.query;

import io.github.winter.boot.filter.RangeFilter;

/**
 * 范围
 *
 * @author changebooks@qq.com
 */
public class QueryRangeFilter extends RangeFilter {
    /**
     * 开始子查询
     */
    private Query fromSubQuery;

    /**
     * 开始子查询名
     */
    private String fromSubQueryName;

    /**
     * 结束子查询
     */
    private Query toSubQuery;

    /**
     * 结束子查询名
     */
    private String toSubQueryName;

}
