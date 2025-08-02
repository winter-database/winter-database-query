package io.github.winter.database.query.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Query Filter Expression
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterExpression implements Serializable {
    /**
     * Filter Expression Id
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
     * Expression Code
     */
    private int expressionCode;

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

}
