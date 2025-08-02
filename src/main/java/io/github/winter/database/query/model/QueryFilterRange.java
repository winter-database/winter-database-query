package io.github.winter.database.query.model;

import io.github.winter.boot.filter.Parameter;

/**
 * 范围
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterRange extends QueryFilter {
    /**
     * 包含开始？
     */
    private Boolean includeLower;

    /**
     * 包含结束？
     */
    private Boolean includeUpper;

    /**
     * 子查询
     */
    private Query fromSubQuery;

    /**
     * 开始参数
     */
    private Parameter from;

    /**
     * 子查询
     */
    private Query toSubQuery;

    /**
     * 结束参数
     */
    private Parameter to;

}
