package io.github.winter.database.query.model;

import io.github.winter.boot.filter.Parameter;

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
    private int wildcardCode;

    /**
     * 参数
     */
    private Parameter parameter;

}
