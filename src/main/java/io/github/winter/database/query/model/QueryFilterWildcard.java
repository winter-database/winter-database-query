package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * Query Filter Wildcard
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterWildcard implements Serializable {
    /**
     * Filter Wildcard Id
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
     * NOT LIKE ?
     */
    private Boolean not;

    /**
     * Wildcard Code CONTAINS STARTS ENDS
     */
    private int wildcardCode;

    /**
     * Sub Query Id
     */
    private int valueId;

    /**
     * Value Name used for REFERENCE PARAMETER
     */
    private String valueName;

    /**
     * Value String
     */
    private String valueString;

}
