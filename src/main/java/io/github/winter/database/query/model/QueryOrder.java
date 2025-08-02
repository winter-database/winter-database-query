package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * Query Order
 *
 * @author changebooks@qq.com
 */
public final class QueryOrder implements Serializable {
    /**
     * Order Id
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
     * DESC ?
     */
    private Boolean desc;

    /**
     * Show Priority used for ORDER BY
     */
    private int showPriority;

}
