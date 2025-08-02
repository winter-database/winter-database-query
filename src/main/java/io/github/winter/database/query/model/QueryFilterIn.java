package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * Query Filter In
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterIn implements Serializable {
    /**
     * Filter In Id
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
     * NOT IN ?
     */
    private Boolean not;

    /**
     * Sub Query Id
     */
    private int valueId;

    /**
     * Value Name used for REFERENCE PARAMETER LIST
     */
    private String valueName;

}
