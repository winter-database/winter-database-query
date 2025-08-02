package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * Query Filter Null
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterNull implements Serializable {
    /**
     * Filter Null Id
     */
    private int id;

    /**
     * Query Id
     */
    private int queryId;

    /**
     * Filter Id
     */
    private int filterId;

    /**
     * NOT NULL ?
     */
    private Boolean not;

}
