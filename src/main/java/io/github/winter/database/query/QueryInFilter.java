package io.github.winter.database.query;

import io.github.winter.boot.filter.InFilter;

/**
 * 在列表中？
 *
 * @author changebooks@qq.com
 */
public class QueryInFilter extends InFilter {
    /**
     * 参数名
     */
    private String parameterName;

    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 子查询名
     */
    private String subQueryName;

}
