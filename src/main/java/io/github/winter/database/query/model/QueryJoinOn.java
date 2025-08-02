package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * Query Join On
 *
 * @author changebooks@qq.com
 */
public final class QueryJoinOn implements Serializable {
    /**
     * Join On Id
     */
    private int id;

    /**
     * Query Id
     */
    private int queryId;

    /**
     * Join Id
     */
    private int joinId;

    /**
     * OR ?
     */
    private Boolean or;

    /**
     * Left Table Name
     */
    private String leftTable;

    /**
     * Left Column Name
     */
    private String leftColumn;

    /**
     * Right Table Name
     */
    private String rightTable;

    /**
     * Right Column Name
     */
    private String rightColumn;

    /**
     * Show Priority used for ORDER BY
     */
    private int showPriority;

}
