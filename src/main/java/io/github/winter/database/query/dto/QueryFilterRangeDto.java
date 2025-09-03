package io.github.winter.database.query.dto;

import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.BooleanCast;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 范围
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterRangeDto implements Serializable {
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
     * 更新版本
     */
    private Integer updateVersion;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date lastUpdate;

    public int getId() {
        return id != null ? id : 0;
    }

    public void setId(Value value) {
        Integer id = value != null ? value.getInteger() : null;
        setId(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQueryId() {
        return queryId != null ? queryId : 0;
    }

    public void setQueryId(Value value) {
        Integer queryId = value != null ? value.getInteger() : null;
        setQueryId(queryId);
    }

    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }

    public int getFilterId() {
        return filterId != null ? filterId : 0;
    }

    public void setFilterId(Value value) {
        Integer filterId = value != null ? value.getInteger() : null;
        setFilterId(filterId);
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    public Boolean getIncludeLower() {
        return isIncludeLower;
    }

    public void setIncludeLower(Value value) {
        Integer includeLower = value != null ? value.getInteger() : null;
        Boolean isIncludeLower = BooleanCast.fromInt(includeLower);
        setIncludeLower(isIncludeLower);
    }

    public void setIncludeLower(Boolean includeLower) {
        isIncludeLower = includeLower;
    }

    public Boolean getIncludeUpper() {
        return isIncludeUpper;
    }

    public void setIncludeUpper(Value value) {
        Integer includeUpper = value != null ? value.getInteger() : null;
        Boolean isIncludeUpper = BooleanCast.fromInt(includeUpper);
        setIncludeUpper(isIncludeUpper);
    }

    public void setIncludeUpper(Boolean includeUpper) {
        isIncludeUpper = includeUpper;
    }

    public String getFromValueString() {
        return fromValueString;
    }

    public void setFromValueString(Value value) {
        String fromValueString = value != null ? value.getString() : null;
        setFromValueString(fromValueString);
    }

    public void setFromValueString(String fromValueString) {
        this.fromValueString = fromValueString;
    }

    public Integer getFromValueInteger() {
        return fromValueInteger;
    }

    public void setFromValueInteger(Value value) {
        Integer fromValueInteger = value != null ? value.getInteger() : null;
        setFromValueInteger(fromValueInteger);
    }

    public void setFromValueInteger(Integer fromValueInteger) {
        this.fromValueInteger = fromValueInteger;
    }

    public Long getFromValueLong() {
        return fromValueLong;
    }

    public void setFromValueLong(Value value) {
        Long fromValueLong = value != null ? value.getLong() : null;
        setFromValueLong(fromValueLong);
    }

    public void setFromValueLong(Long fromValueLong) {
        this.fromValueLong = fromValueLong;
    }

    public BigDecimal getFromValueBigDecimal() {
        return fromValueBigDecimal;
    }

    public void setFromValueBigDecimal(Value value) {
        BigDecimal fromValueBigDecimal = value != null ? value.getBigDecimal() : null;
        setFromValueBigDecimal(fromValueBigDecimal);
    }

    public void setFromValueBigDecimal(BigDecimal fromValueBigDecimal) {
        this.fromValueBigDecimal = fromValueBigDecimal;
    }

    public Date getFromValueDate() {
        return fromValueDate;
    }

    public void setFromValueDate(Value value) {
        Date fromValueDate = value != null ? value.getDate() : null;
        setFromValueDate(fromValueDate);
    }

    public void setFromValueDate(Date fromValueDate) {
        this.fromValueDate = fromValueDate;
    }

    public String getToValueString() {
        return toValueString;
    }

    public void setToValueString(Value value) {
        String toValueString = value != null ? value.getString() : null;
        setToValueString(toValueString);
    }

    public void setToValueString(String toValueString) {
        this.toValueString = toValueString;
    }

    public Integer getToValueInteger() {
        return toValueInteger;
    }

    public void setToValueInteger(Value value) {
        Integer toValueInteger = value != null ? value.getInteger() : null;
        setToValueInteger(toValueInteger);
    }

    public void setToValueInteger(Integer toValueInteger) {
        this.toValueInteger = toValueInteger;
    }

    public Long getToValueLong() {
        return toValueLong;
    }

    public void setToValueLong(Value value) {
        Long toValueLong = value != null ? value.getLong() : null;
        setToValueLong(toValueLong);
    }

    public void setToValueLong(Long toValueLong) {
        this.toValueLong = toValueLong;
    }

    public BigDecimal getToValueBigDecimal() {
        return toValueBigDecimal;
    }

    public void setToValueBigDecimal(Value value) {
        BigDecimal toValueBigDecimal = value != null ? value.getBigDecimal() : null;
        setToValueBigDecimal(toValueBigDecimal);
    }

    public void setToValueBigDecimal(BigDecimal toValueBigDecimal) {
        this.toValueBigDecimal = toValueBigDecimal;
    }

    public Date getToValueDate() {
        return toValueDate;
    }

    public void setToValueDate(Value value) {
        Date toValueDate = value != null ? value.getDate() : null;
        setToValueDate(toValueDate);
    }

    public void setToValueDate(Date toValueDate) {
        this.toValueDate = toValueDate;
    }

    public int getUpdateVersion() {
        return updateVersion != null ? updateVersion : 0;
    }

    public void setUpdateVersion(Value value) {
        Integer updateVersion = value != null ? value.getInteger() : null;
        setUpdateVersion(updateVersion);
    }

    public void setUpdateVersion(Integer updateVersion) {
        this.updateVersion = updateVersion;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Value value) {
        Date createDate = value != null ? value.getDate() : null;
        setCreateDate(createDate);
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Value value) {
        Date lastUpdate = value != null ? value.getDate() : null;
        setLastUpdate(lastUpdate);
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
