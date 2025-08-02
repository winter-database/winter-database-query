package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * Query Join
 *
 * @author changebooks@qq.com
 */
public final class QueryJoin implements Serializable {
    /**
     * Join Id
     */
    private int id;

    /**
     * Query Id
     */
    private int queryId;

    /**
     * Join Table Name
     */
    private String joinTable;

    /**
     * Join Sub Query Id
     */
    private int joinId;

    /**
     * Join Type INNER OUTER LEFT RIGHT FULL CROSS SELF NATURAL STRAIGHT
     */
    private int joinType;

    /**
     * Show Priority used for ORDER BY
     */
    private int showPriority;

}
