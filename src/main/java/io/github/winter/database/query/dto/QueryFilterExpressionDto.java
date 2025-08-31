package io.github.winter.database.query.dto;

import io.github.winter.boot.tuple.Value;
import io.github.winter.database.template.Template;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 表达式
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterExpressionDto implements Serializable {
    /**
     * 表名
     */
    public static final String TABLE_NAME = "xquery_filter_expression";

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

    /**
     * Read Instance List
     *
     * @param template the {@link Template} instance
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return [ the {@link QueryFilterExpressionDto} instance ]
     */
    public static List<QueryFilterExpressionDto> readInstances(@NotNull Template template, int queryId, int filterId) {
        List<Map<String, Value>> list = DtoUtils.selectList(template, TABLE_NAME, queryId, filterId);
        return newInstances(list);
    }

    /**
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryFilterExpressionDto} instance ]
     */
    public static List<QueryFilterExpressionDto> newInstances(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryFilterExpressionDto::newInstance)
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
     * @return the {@link QueryFilterExpressionDto} instance
     */
    public static QueryFilterExpressionDto newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value filterId = record.get("filter_id");
        Value expressionCode = record.get("expression_code");
        Value valueString = record.get("value_string");
        Value valueInteger = record.get("value_integer");
        Value valueLong = record.get("value_long");
        Value valueBigDecimal = record.get("value_big_decimal");
        Value valueDate = record.get("value_date");
        Value updateVersion = record.get("update_version");
        Value createDate = record.get("create_date");
        Value lastUpdate = record.get("last_update");

        QueryFilterExpressionDto result = new QueryFilterExpressionDto();

        result.setId(id);
        result.setQueryId(queryId);
        result.setFilterId(filterId);
        result.setExpressionCode(expressionCode);
        result.setValueString(valueString);
        result.setValueInteger(valueInteger);
        result.setValueLong(valueLong);
        result.setValueBigDecimal(valueBigDecimal);
        result.setValueDate(valueDate);
        result.setUpdateVersion(updateVersion);
        result.setCreateDate(createDate);
        result.setLastUpdate(lastUpdate);

        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Value value) {
        Integer id = value != null ? value.getInteger() : null;
        setId(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQueryId() {
        return queryId;
    }

    public void setQueryId(Value value) {
        Integer queryId = value != null ? value.getInteger() : null;
        setQueryId(queryId);
    }

    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Value value) {
        Integer filterId = value != null ? value.getInteger() : null;
        setFilterId(filterId);
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    public Integer getExpressionCode() {
        return expressionCode;
    }

    public void setExpressionCode(Value value) {
        Integer expressionCode = value != null ? value.getInteger() : null;
        setExpressionCode(expressionCode);
    }

    public void setExpressionCode(Integer expressionCode) {
        this.expressionCode = expressionCode;
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

    public Integer getUpdateVersion() {
        return updateVersion;
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
