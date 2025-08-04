package io.github.winter.database.query.model;

import io.github.winter.boot.filter.Parameter;

import java.io.Serializable;

/**
 * 参数
 *
 * @author changebooks@qq.com
 */
public final class QueryParameter implements Serializable {
    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 参数
     */
    private Parameter parameter;

    public Query getSubQuery() {
        return subQuery;
    }

    public void setSubQuery(Query subQuery) {
        this.subQuery = subQuery;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

}
