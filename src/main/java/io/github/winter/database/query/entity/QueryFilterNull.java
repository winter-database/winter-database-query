package io.github.winter.database.query.entity;

import io.github.winter.boot.tuple.Value;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 空
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterNull implements Serializable {
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
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryFilterNull} instance ]
     */
    public static List<QueryFilterNull> newInstance(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryFilterNull::newInstance)
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
     * @return the {@link QueryFilterNull} instance
     */
    public static QueryFilterNull newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value filterId = record.get("filter_id");
        Value isNot = record.get("is_not");

        QueryFilterNull result = new QueryFilterNull();

        result.setId(id);
        result.setQueryId(queryId);
        result.setFilterId(filterId);
        result.setNot(isNot);

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

}
