package io.github.winter.database.query.entity;

import io.github.winter.boot.tuple.Value;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 连表
 *
 * @author changebooks@qq.com
 */
public final class QueryJoin implements Serializable {
    /**
     * 主键
     */
    private int id;

    /**
     * 查询主键
     */
    private int queryId;

    /**
     * 表名
     */
    private String joinTable;

    /**
     * 连表方式
     */
    private int joinType;

    /**
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryJoin} instance ]
     */
    public static List<QueryJoin> newInstance(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryJoin::newInstance)
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
     * @return the {@link QueryJoin} instance
     */
    public static QueryJoin newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value joinTable = record.get("join_table");
        Value joinType = record.get("join_type");

        QueryJoin result = new QueryJoin();

        result.setId(id);
        result.setQueryId(queryId);
        result.setJoinTable(joinTable);
        result.setJoinType(joinType);

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

    @NotNull
    public String getJoinTable() {
        return joinTable != null ? joinTable : "";
    }

    public void setJoinTable(Value value) {
        String joinTable = value != null ? value.getString() : null;
        setJoinTable(joinTable);
    }

    public void setJoinTable(String joinTable) {
        this.joinTable = joinTable != null ? joinTable.trim() : "";
    }

    public int getJoinType() {
        return joinType;
    }

    public void setJoinType(Value value) {
        int joinType = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setJoinType(joinType);
    }

    public void setJoinType(int joinType) {
        this.joinType = joinType;
    }

}
