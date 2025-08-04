package io.github.winter.database.query.model;

import java.io.Serializable;
import java.util.List;

/**
 * 分组
 *
 * @author changebooks@qq.com
 */
public final class QueryGroup implements Serializable {
    /**
     * 字段列表
     */
    private List<QueryColumn> columns;

    /**
     * 条件列表
     */
    private List<QueryFilter> filters;

    public List<QueryColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<QueryColumn> columns) {
        this.columns = columns;
    }

    public List<QueryFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<QueryFilter> filters) {
        this.filters = filters;
    }

}
