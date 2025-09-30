package io.github.winter.database.query.entity;

import io.github.winter.boot.tuple.Value;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 表达式
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterExpression implements Serializable {
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
     * 编码
     */
    private int expressionCode;

    /**
     * 参数名
     */
    private String parameterName;

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
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryFilterExpression} instance ]
     */
    public static List<QueryFilterExpression> newInstance(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryFilterExpression::newInstance)
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
     * @return the {@link QueryFilterExpression} instance
     */
    public static QueryFilterExpression newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value filterId = record.get("filter_id");
        Value expressionCode = record.get("expression_code");
        Value parameterName = record.get("parameter_name");
        Value valueString = record.get("value_string");
        Value valueInteger = record.get("value_integer");
        Value valueLong = record.get("value_long");
        Value valueBigDecimal = record.get("value_big_decimal");
        Value valueDate = record.get("value_date");

        QueryFilterExpression result = new QueryFilterExpression();

        result.setId(id);
        result.setQueryId(queryId);
        result.setFilterId(filterId);
        result.setExpressionCode(expressionCode);
        result.setParameterName(parameterName);
        result.setValueString(valueString);
        result.setValueInteger(valueInteger);
        result.setValueLong(valueLong);
        result.setValueBigDecimal(valueBigDecimal);
        result.setValueDate(valueDate);

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

    public int getExpressionCode() {
        return expressionCode;
    }

    public void setExpressionCode(Value value) {
        int expressionCode = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setExpressionCode(expressionCode);
    }

    public void setExpressionCode(int expressionCode) {
        this.expressionCode = expressionCode;
    }

    @NotNull
    public String getParameterName() {
        return parameterName != null ? parameterName : "";
    }

    public void setParameterName(Value value) {
        String parameterName = value != null ? value.getString() : null;
        setParameterName(parameterName);
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName != null ? parameterName.trim() : "";
    }

    @NotNull
    public Value getValue(Class<?> valueType) {
        String valueString = getValueString();
        Integer valueInteger = getValueInteger();
        Long valueLong = getValueLong();
        BigDecimal valueBigDecimal = getValueBigDecimal();
        Date valueDate = getValueDate();
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

    public String getValueString() {
        return valueString;
    }

    public void setValueString(Value value) {
        String valueString = value != null ? value.getString() : null;
        setValueString(valueString);
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public Integer getValueInteger() {
        return valueInteger;
    }

    public void setValueInteger(Value value) {
        Integer valueInteger = value != null ? value.getInteger() : null;
        setValueInteger(valueInteger);
    }

    public void setValueInteger(Integer valueInteger) {
        this.valueInteger = valueInteger;
    }

    public Long getValueLong() {
        return valueLong;
    }

    public void setValueLong(Value value) {
        Long valueLong = value != null ? value.getLong() : null;
        setValueLong(valueLong);
    }

    public void setValueLong(Long valueLong) {
        this.valueLong = valueLong;
    }

    public BigDecimal getValueBigDecimal() {
        return valueBigDecimal;
    }

    public void setValueBigDecimal(Value value) {
        BigDecimal valueBigDecimal = value != null ? value.getBigDecimal() : null;
        setValueBigDecimal(valueBigDecimal);
    }

    public void setValueBigDecimal(BigDecimal valueBigDecimal) {
        this.valueBigDecimal = valueBigDecimal;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Value value) {
        Date valueDate = value != null ? value.getDate() : null;
        setValueDate(valueDate);
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

}
