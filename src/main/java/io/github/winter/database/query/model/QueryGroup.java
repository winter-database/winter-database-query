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

}
