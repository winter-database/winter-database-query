package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * Query Filter
 *
 * @author changebooks@qq.com
 */
public final class QueryFilter implements Serializable {
    /**
     * Filter Id
     */
    private int id;

    /**
     * Query Id
     */
    private int queryId;

    /**
     * Group Id used for HAVING, if 0 used for WHERE
     */
    private int groupId;

    /**
     * Parent Id used for Hierarchical Tree e.g. (filter AND (filter OR filter))
     */
    private int parentId;

    /**
     * OR ?
     */
    private Boolean or;

    /**
     * Table Name
     */
    private String tableName;

    /**
     * Column Name
     */
    private String columnName;

    /**
     * Aggregate Func
     */
    private int aggregateFunc;

    /**
     * Filter Type
     */
    private int filterType;

    /**
     * Show Priority used for ORDER BY
     */
    private int showPriority;

}
