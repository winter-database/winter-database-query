package io.github.winter.database.query.model;

import io.github.winter.boot.tuple.Value;

import java.util.List;

/**
 * 在列表中
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterIn extends QueryFilter {
    /**
     * 取反？
     */
    private Boolean not;

    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 参数名
     */
    private String parameterName;

    /**
     * 值列表
     */
    private List<Value> values;

    public Boolean getNot() {
        return not;
    }

    public void setNot(Boolean not) {
        this.not = not;
    }

    public Query getSubQuery() {
        return subQuery;
    }

    public void setSubQuery(Query subQuery) {
        this.subQuery = subQuery;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

}
