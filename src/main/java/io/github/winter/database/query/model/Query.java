package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * Query
 *
 * @author changebooks@qq.com
 */
public final class Query implements Serializable {
    /**
     * Query Id
     */
    private int id;

    /**
     * Query Name
     */
    private String queryName;

    /**
     * DISTINCT ?
     */
    private boolean distinct;

    /**
     * From Table Name
     */
    private String fromTable;

    /**
     * From Sub Query Id
     */
    private int fromId;

    /**
     * Page Type OFFSET ID_RANGE
     */
    private int pageType;

    /**
     * Page Limit
     */
    private int pageSize;

    /**
     * Query Description
     */
    private String queryDescription;

    /**
     * Query Remark
     */
    private String queryRemark;

    /**
     * Show Priority used for ORDER BY
     */
    private int showPriority;

}
