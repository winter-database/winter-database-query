package io.github.winter.database.query.model;

import io.github.winter.boot.filter.Parameter;

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
     * 参数列表
     */
    private List<Parameter> parameters;

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

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

}
