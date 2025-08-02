package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * Query Group
 *
 * @author changebooks@qq.com
 */
public final class QueryGroup implements Serializable {
    /**
     * Group Id
     */
    private int id;

    /**
     * Query Id
     */
    private int queryId;

    /**
     * Table Name
     */
    private String tableName;

    /**
     * Column Name
     */
    private String columnName;

    /**
     * Aggregate Func COUNT SUM MAX MIN AVG
     */
    private int aggregateFunc;

    /**
     * Show Priority used for ORDER BY
     */
    private int showPriority;

}
