package io.github.winter.database.query.model;

/**
 * 范围
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterRange extends QueryFilter {
    /**
     * 开始参数
     */
    private QueryParameter from;

    /**
     * 结束参数
     */
    private QueryParameter to;

    /**
     * 包含开始？
     */
    private Boolean includeLower;

    /**
     * 包含结束？
     */
    private Boolean includeUpper;

    public QueryParameter getFrom() {
        return from;
    }

    public void setFrom(QueryParameter from) {
        this.from = from;
    }

    public QueryParameter getTo() {
        return to;
    }

    public void setTo(QueryParameter to) {
        this.to = to;
    }

    public Boolean getIncludeLower() {
        return includeLower;
    }

    public void setIncludeLower(Boolean includeLower) {
        this.includeLower = includeLower;
    }

    public Boolean getIncludeUpper() {
        return includeUpper;
    }

    public void setIncludeUpper(Boolean includeUpper) {
        this.includeUpper = includeUpper;
    }

}
