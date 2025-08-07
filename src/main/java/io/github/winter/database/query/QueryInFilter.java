package io.github.winter.database.query;

import io.github.winter.boot.filter.InFilter;
import jakarta.validation.constraints.NotNull;

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

    @NotNull
    public String getParameterName() {
        return parameterName != null ? parameterName : "";
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName != null ? parameterName.trim() : "";
    }

    public Query getSubQuery() {
        return subQuery;
    }

    public void setSubQuery(Query subQuery) {
        this.subQuery = subQuery;
    }

    @NotNull
    public String getSubQueryName() {
        return subQueryName != null ? subQueryName : "";
    }

    public void setSubQueryName(String subQueryName) {
        this.subQueryName = subQueryName != null ? subQueryName.trim() : "";
    }

}
