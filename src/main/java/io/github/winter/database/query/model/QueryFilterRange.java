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

}
