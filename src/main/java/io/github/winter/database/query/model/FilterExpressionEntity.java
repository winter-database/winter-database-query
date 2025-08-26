package io.github.winter.database.query.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表达式
 *
 * @author changebooks@qq.com
 */
public final class FilterExpressionEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 查询主键
     */
    private Integer queryId;

    /**
     * 条件主键
     */
    private Integer filterId;

    /**
     * 编码
     */
    private Integer expressionCode;

    /**
     * 字符串
     */
    private String valueString;

    /**
     * 整数
     */
    private Integer valueInteger;

    /**
     * 长整数
     */
    private Long valueLong;

    /**
     * 小数
     */
    private BigDecimal valueBigDecimal;

    /**
     * 日期时间
     */
    private Date valueDate;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date lastUpdate;

}
