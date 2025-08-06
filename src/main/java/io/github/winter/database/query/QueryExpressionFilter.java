package io.github.winter.database.query;

import io.github.winter.boot.filter.ExpressionFilter;

/**
 * 表达式
 *
 * @author changebooks@qq.com
 */
public class QueryExpressionFilter extends ExpressionFilter {
    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 子查询名
     */
    private String subQueryName;

}
