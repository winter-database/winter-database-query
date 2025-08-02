package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * Query Column
 *
 * @author changebooks@qq.com
 */
public final class QueryColumn implements Serializable {
    /**
     * Column Id
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
     * Aggregate Func
     */
    private int aggregateFunc;

    /**
     * Show Priority used for ORDER BY
     */
    private int showPriority;

}
