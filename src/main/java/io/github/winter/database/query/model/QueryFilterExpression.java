package io.github.winter.database.query.model;

/**
 * 表达式
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterExpression extends QueryFilter {
    /**
     * 编码
     */
    private int code;

    /**
     * 参数
     */
    private QueryParameter parameter;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public QueryParameter getParameter() {
        return parameter;
    }

    public void setParameter(QueryParameter parameter) {
        this.parameter = parameter;
    }

}
