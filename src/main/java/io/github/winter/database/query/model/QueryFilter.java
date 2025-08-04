package io.github.winter.database.query.model;

import java.io.Serializable;
import java.util.List;

/**
 * 条件
 *
 * @author changebooks@qq.com
 */
public class QueryFilter implements Serializable {
    /**
     * 子条件列表
     */
    private List<QueryFilter> filters;

    /**
     * 字段
     */
    private QueryColumn column;

    /**
     * 逻辑或？
     */
    private Boolean or;

    public List<QueryFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<QueryFilter> filters) {
        this.filters = filters;
    }

    public QueryColumn getColumn() {
        return column;
    }

    public void setColumn(QueryColumn column) {
        this.column = column;
    }

    public Boolean getOr() {
        return or;
    }

    public void setOr(Boolean or) {
        this.or = or;
    }

}
