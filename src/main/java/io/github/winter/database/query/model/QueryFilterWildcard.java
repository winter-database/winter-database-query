package io.github.winter.database.query.model;

/**
 * 模糊匹配
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterWildcard extends QueryFilter {
    /**
     * 取反？
     */
    private Boolean not;

    /**
     * 编码
     */
    private int code;

    /**
     * 参数
     */
    private QueryParameter parameter;

    public Boolean getNot() {
        return not;
    }

    public void setNot(Boolean not) {
        this.not = not;
    }

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
