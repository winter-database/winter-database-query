package io.github.winter.database.query;

import io.github.winter.boot.filter.WildcardFilter;

/**
 * 模糊匹配
 *
 * @author changebooks@qq.com
 */
public class QueryWildcardFilter extends WildcardFilter {
    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 子查询名
     */
    private String subQueryName;

}
