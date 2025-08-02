package io.github.winter.database.query.model;

import io.github.winter.boot.filter.Parameter;

/**
 * 表达式
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterExpression extends QueryFilter {
    /**
     * 编码
     */
    private int expressionCode;

    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 参数
     */
    private Parameter parameter;

}
