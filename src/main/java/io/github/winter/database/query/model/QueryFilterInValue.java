package io.github.winter.database.query.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Query Filter In Value
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterInValue implements Serializable {
    /**
     * Filter In Value Id
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
     * Filter In Id
     */
    private int filterInId;

    /**
     * Value String
     */
    private String valueString;

    /**
     * Value Integer
     */
    private Integer valueInteger;

    /**
     * Value Long
     */
    private Long valueLong;

    /**
     * Value Big Decimal
     */
    private BigDecimal valueBigDecimal;

    /**
     * Value Date
     */
    private Date valueDate;

    /**
     * Show Priority used for ORDER BY
     */
    private int showPriority;

}
