package io.github.winter.database.query.model;

import io.github.winter.boot.filter.Parameter;

import java.util.List;

/**
 * 在列表中？
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

}
