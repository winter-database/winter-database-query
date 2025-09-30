package io.github.winter.database.query.entity;

import io.github.winter.boot.tuple.Value;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 查询
 *
 * @author changebooks@qq.com
 */
public final class Query implements Serializable {
    /**
     * 主键
     */
    private int id;

    /**
     * 名称
     */
    private String queryName;

    /**
     * 去重？
     */
    private boolean distinct;

    /**
     * 全字段？
     */
    private boolean asterisk;

    /**
     * 忽略参数名？
     */
    private boolean parameterName;

    /**
     * 表名
     */
    private String fromTable;

    /**
     * 开始行数
     */
    private long pageOffset;

    /**
     * 每页行数
     */
    private int pageLimit;

    /**
     * 描述
     */
    private String queryDescription;

    /**
     * 备注
     */
    private String queryRemark;

    /**
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link Query} instance ]
     */
    public static List<Query> newInstance(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(Query::newInstance)
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
     * @return the {@link Query} instance
     */
    public static Query newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryName = record.get("query_name");
        Value isDistinct = record.get("is_distinct");
        Value isAsterisk = record.get("is_asterisk");
        Value isParameterName = record.get("is_parameter_name");
        Value fromTable = record.get("from_table");
        Value pageOffset = record.get("page_offset");
        Value pageLimit = record.get("page_limit");
        Value queryDescription = record.get("query_description");
        Value queryRemark = record.get("query_remark");

        Query result = new Query();

        result.setId(id);
        result.setQueryName(queryName);
        result.setDistinct(isDistinct);
        result.setAsterisk(isAsterisk);
        result.setParameterName(isParameterName);
        result.setFromTable(fromTable);
        result.setPageOffset(pageOffset);
        result.setPageLimit(pageLimit);
        result.setQueryDescription(queryDescription);
        result.setQueryRemark(queryRemark);

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

    @NotNull
    public String getQueryName() {
        return queryName != null ? queryName : "";
    }

    public void setQueryName(Value value) {
        String queryName = value != null ? value.getString() : null;
        setQueryName(queryName);
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName != null ? queryName.trim() : "";
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(Value value) {
        Integer distinct = value != null ? value.getInteger() : null;
        boolean isDistinct = BooleanCast.fromInt(distinct);
        setDistinct(isDistinct);
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isAsterisk() {
        return asterisk;
    }

    public void setAsterisk(Value value) {
        Integer asterisk = value != null ? value.getInteger() : null;
        boolean isAsterisk = BooleanCast.fromInt(asterisk);
        setAsterisk(isAsterisk);
    }

    public void setAsterisk(boolean asterisk) {
        this.asterisk = asterisk;
    }

    public boolean isParameterName() {
        return parameterName;
    }

    public void setParameterName(Value value) {
        Integer parameterName = value != null ? value.getInteger() : null;
        boolean isParameterName = BooleanCast.fromInt(parameterName);
        setParameterName(isParameterName);
    }

    public void setParameterName(boolean parameterName) {
        this.parameterName = parameterName;
    }

    @NotNull
    public String getFromTable() {
        return fromTable != null ? fromTable : "";
    }

    public void setFromTable(Value value) {
        String fromTable = value != null ? value.getString() : null;
        setFromTable(fromTable);
    }

    public void setFromTable(String fromTable) {
        this.fromTable = fromTable != null ? fromTable.trim() : "";
    }

    public long getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Value value) {
        long pageOffset = Optional.ofNullable(value).map(Value::getLong).orElse(0L);
        setPageOffset(pageOffset);
    }

    public void setPageOffset(long pageOffset) {
        this.pageOffset = pageOffset;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Value value) {
        int pageLimit = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setPageLimit(pageLimit);
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    @NotNull
    public String getQueryDescription() {
        return queryDescription != null ? queryDescription : "";
    }

    public void setQueryDescription(Value value) {
        String queryDescription = value != null ? value.getString() : null;
        setQueryDescription(queryDescription);
    }

    public void setQueryDescription(String queryDescription) {
        this.queryDescription = queryDescription != null ? queryDescription.trim() : "";
    }

    @NotNull
    public String getQueryRemark() {
        return queryRemark != null ? queryRemark : "";
    }

    public void setQueryRemark(Value value) {
        String queryRemark = value != null ? value.getString() : null;
        setQueryRemark(queryRemark);
    }

    public void setQueryRemark(String queryRemark) {
        this.queryRemark = queryRemark != null ? queryRemark.trim() : "";
    }

}
