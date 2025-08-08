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

    public Query getSubQuery() {
        return subQuery;
    }

    public void setSubQuery(Query subQuery) {
        this.subQuery = subQuery;
    }

    public String getSubQueryName() {
        return subQueryName;
    }

    public void setSubQueryName(String subQueryName) {
        this.subQueryName = subQueryName != null ? subQueryName.trim() : "";
    }

}
