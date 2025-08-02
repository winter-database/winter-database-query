package io.github.winter.database.query.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Query Filter Range
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterRange implements Serializable {
    /**
     * Filter Range Id
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
     * Include Lower ?
     */
    private Boolean includeLower;

    /**
     * Include Upper ?
     */
    private Boolean includeUpper;

    /**
     * From Sub Query Id
     */
    private Integer fromValueId;

    /**
     * From Value Name used for REFERENCE PARAMETER
     */
    private String fromValueName;

    /**
     * From Value String
     */
    private String fromValueString;

    /**
     * From Value Integer
     */
    private Integer fromValueInteger;

    /**
     * From Value Long
     */
    private Long fromValueLong;

    /**
     * From Value Big Decimal
     */
    private BigDecimal fromValueBigDecimal;

    /**
     * From Value Date
     */
    private Date fromValueDate;

    /**
     * To Sub Query Id
     */
    private Integer toValueId;

    /**
     * To Value Name used for REFERENCE PARAMETER
     */
    private String toValueName;

    /**
     * To Value String
     */
    private String toValueString;

    /**
     * To Value Integer
     */
    private Integer toValueInteger;

    /**
     * To Value Long
     */
    private Long toValueLong;

    /**
     * To Value Big Decimal
     */
    private BigDecimal toValueBigDecimal;

    /**
     * To Value Date
     */
    private Date toValueDate;

}
