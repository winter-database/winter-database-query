package io.github.winter.database.query.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 范围
 *
 * @author changebooks@qq.com
 */
public final class FilterRangeEntity implements Serializable {
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
     * 包含开始？
     */
    private Boolean isIncludeLower;

    /**
     * 包含结束？
     */
    private Boolean isIncludeUpper;

    /**
     * 开始字符串
     */
    private String fromValueString;

    /**
     * 开始整数
     */
    private Integer fromValueInteger;

    /**
     * 开始长整数
     */
    private Long fromValueLong;

    /**
     * 开始小数
     */
    private BigDecimal fromValueBigDecimal;

    /**
     * 开始日期时间
     */
    private Date fromValueDate;

    /**
     * 结束字符串
     */
    private String toValueString;

    /**
     * 结束整数
     */
    private Integer toValueInteger;

    /**
     * 结束长整数
     */
    private Long toValueLong;

    /**
     * 结束小数
     */
    private BigDecimal toValueBigDecimal;

    /**
     * 结束日期时间
     */
    private Date toValueDate;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date lastUpdate;

}
