package io.github.winter.database.query;

import io.github.winter.boot.filter.RangeFilter;
import jakarta.validation.constraints.NotNull;

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

    public Query getFromSubQuery() {
        return fromSubQuery;
    }

    public void setFromSubQuery(Query fromSubQuery) {
        this.fromSubQuery = fromSubQuery;
    }

    public String getFromSubQueryName() {
        return fromSubQueryName;
    }

    public void setFromSubQueryName(String fromSubQueryName) {
        this.fromSubQueryName = fromSubQueryName != null ? fromSubQueryName.trim() : "";
    }

    public Query getToSubQuery() {
        return toSubQuery;
    }

    public void setToSubQuery(Query toSubQuery) {
        this.toSubQuery = toSubQuery;
    }

    public String getToSubQueryName() {
        return toSubQueryName;
    }

    public void setToSubQueryName(String toSubQueryName) {
        this.toSubQueryName = toSubQueryName != null ? toSubQueryName.trim() : "";
    }

}
