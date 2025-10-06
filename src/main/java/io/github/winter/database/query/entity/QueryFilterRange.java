package io.github.winter.database.query.entity;

import io.github.winter.boot.tuple.Value;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 范围
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterRange implements Serializable {
    /**
     * 主键
     */
    private int id;

    /**
     * 查询主键
     */
    private int queryId;

    /**
     * 条件主键
     */
    private int filterId;

    /**
     * 包含开始？
     */
    private boolean includeLower;

    /**
     * 包含结束？
     */
    private boolean includeUpper;

    /**
     * 开始参数名
     */
    private String fromParameterName;

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
     * 结束参数名
     */
    private String toParameterName;

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
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryFilterRange} instance ]
     */
    public static List<QueryFilterRange> newInstance(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryFilterRange::newInstance)
                    .filter(Objects::nonNull)
                    .toList();
        } else {
            return null;
        }
    }

    /**
     * Build Instance
     *
     * @param record [ Column Name : Column Value ]
     * @return the {@link QueryFilterRange} instance
     */
    public static QueryFilterRange newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value filterId = record.get("filter_id");
        Value isIncludeLower = record.get("is_include_lower");
        Value isIncludeUpper = record.get("is_include_upper");
        Value fromParameterName = record.get("from_parameter_name");
        Value fromValueString = record.get("from_value_string");
        Value fromValueInteger = record.get("from_value_integer");
        Value fromValueLong = record.get("from_value_long");
        Value fromValueBigDecimal = record.get("from_value_big_decimal");
        Value fromValueDate = record.get("from_value_date");
        Value toParameterName = record.get("to_parameter_name");
        Value toValueString = record.get("to_value_string");
        Value toValueInteger = record.get("to_value_integer");
        Value toValueLong = record.get("to_value_long");
        Value toValueBigDecimal = record.get("to_value_big_decimal");
        Value toValueDate = record.get("to_value_date");

        QueryFilterRange result = new QueryFilterRange();

        result.setId(id);
        result.setQueryId(queryId);
        result.setFilterId(filterId);
        result.setIncludeLower(isIncludeLower);
        result.setIncludeUpper(isIncludeUpper);
        result.setFromParameterName(fromParameterName);
        result.setFromValueString(fromValueString);
        result.setFromValueInteger(fromValueInteger);
        result.setFromValueLong(fromValueLong);
        result.setFromValueBigDecimal(fromValueBigDecimal);
        result.setFromValueDate(fromValueDate);
        result.setToParameterName(toParameterName);
        result.setToValueString(toValueString);
        result.setToValueInteger(toValueInteger);
        result.setToValueLong(toValueLong);
        result.setToValueBigDecimal(toValueBigDecimal);
        result.setToValueDate(toValueDate);

        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(Value value) {
        int id = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setId(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQueryId() {
        return queryId;
    }

    public void setQueryId(Value value) {
        int queryId = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setQueryId(queryId);
    }

    public void setQueryId(int queryId) {
        this.queryId = queryId;
    }

    public int getFilterId() {
        return filterId;
    }

    public void setFilterId(Value value) {
        int filterId = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setFilterId(filterId);
    }

    public void setFilterId(int filterId) {
        this.filterId = filterId;
    }

    public boolean isIncludeLower() {
        return includeLower;
    }

    public void setIncludeLower(Value value) {
        Integer includeLower = value != null ? value.getInteger() : null;
        boolean isIncludeLower = BooleanCast.fromInt(includeLower);
        setIncludeLower(isIncludeLower);
    }

    public void setIncludeLower(boolean includeLower) {
        this.includeLower = includeLower;
    }

    public boolean isIncludeUpper() {
        return includeUpper;
    }

    public void setIncludeUpper(Value value) {
        Integer includeUpper = value != null ? value.getInteger() : null;
        boolean isIncludeUpper = BooleanCast.fromInt(includeUpper);
        setIncludeUpper(isIncludeUpper);
    }

    public void setIncludeUpper(boolean includeUpper) {
        this.includeUpper = includeUpper;
    }

    @NotNull
    public String getFromParameterName() {
        return fromParameterName != null ? fromParameterName : "";
    }

    public void setFromParameterName(Value value) {
        String fromParameterName = value != null ? value.getString() : null;
        setFromParameterName(fromParameterName);
    }

    public void setFromParameterName(String fromParameterName) {
        this.fromParameterName = fromParameterName != null ? fromParameterName.trim() : "";
    }

    @NotNull
    public Value getFromValue(Class<?> valueType) {
        String valueString = getFromValueString();
        Integer valueInteger = getFromValueInteger();
        Long valueLong = getFromValueLong();
        BigDecimal valueBigDecimal = getFromValueBigDecimal();
        Date valueDate = getFromValueDate();
        return Value.newInstance
                (
                        valueType,
                        valueString,
                        valueInteger,
                        valueLong,
                        valueBigDecimal,
                        valueDate
                );
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

    @NotNull
    public String getToParameterName() {
        return toParameterName != null ? toParameterName : "";
    }

    public void setToParameterName(Value value) {
        String toParameterName = value != null ? value.getString() : null;
        setToParameterName(toParameterName);
    }

    public void setToParameterName(String toParameterName) {
        this.toParameterName = toParameterName != null ? toParameterName.trim() : "";
    }

    @NotNull
    public Value getToValue(Class<?> valueType) {
        String valueString = getToValueString();
        Integer valueInteger = getToValueInteger();
        Long valueLong = getToValueLong();
        BigDecimal valueBigDecimal = getToValueBigDecimal();
        Date valueDate = getToValueDate();
        return Value.newInstance
                (
                        valueType,
                        valueString,
                        valueInteger,
                        valueLong,
                        valueBigDecimal,
                        valueDate
                );
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

}
