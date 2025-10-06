package io.github.winter.database.query.entity;

import io.github.winter.boot.tuple.Value;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 在列表中
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterIn implements Serializable {
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
     * 取反？
     */
    private boolean not;

    /**
     * 子查询主键
     */
    private int subQueryId;

    /**
     * 参数名
     */
    private String parameterName;

    /**
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryFilterIn} instance ]
     */
    public static List<QueryFilterIn> newInstance(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryFilterIn::newInstance)
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
     * @return the {@link QueryFilterIn} instance
     */
    public static QueryFilterIn newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value filterId = record.get("filter_id");
        Value isNot = record.get("is_not");
        Value subQueryId = record.get("sub_query_id");
        Value parameterName = record.get("parameter_name");

        QueryFilterIn result = new QueryFilterIn();

        result.setId(id);
        result.setQueryId(queryId);
        result.setFilterId(filterId);
        result.setNot(isNot);
        result.setSubQueryId(subQueryId);
        result.setParameterName(parameterName);

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

    public boolean isNot() {
        return not;
    }

    public void setNot(Value value) {
        Integer not = value != null ? value.getInteger() : null;
        boolean isNot = BooleanCast.fromInt(not);
        setNot(isNot);
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    public int getSubQueryId() {
        return subQueryId;
    }

    public void setSubQueryId(Value value) {
        int subQueryId = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setSubQueryId(subQueryId);
    }

    public void setSubQueryId(int subQueryId) {
        this.subQueryId = subQueryId;
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

}
