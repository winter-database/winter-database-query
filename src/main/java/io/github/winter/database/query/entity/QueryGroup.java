package io.github.winter.database.query.entity;

import io.github.winter.boot.tuple.Value;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 分组
 *
 * @author changebooks@qq.com
 */
public final class QueryGroup implements Serializable {
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
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * Build Instance List
     *
     * @param list      [ [ Column Name : Column Value ] ]
     * @param fromTable Table Name
     * @return [ the {@link QueryGroup} instance ]
     */
    public static List<QueryGroup> newInstance(List<Map<String, Value>> list, @NotEmpty String fromTable) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(x -> newInstance(x, fromTable))
                    .filter(Objects::nonNull)
                    .toList();
        } else {
            return null;
        }
    }

    /**
     * Build Instance
     *
     * @param record    [ Column Name : Column Value ]
     * @param fromTable Table Name
     * @return the {@link QueryGroup} instance
     */
    public static QueryGroup newInstance(Map<String, Value> record, @NotEmpty String fromTable) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value tableName = record.get("table_name");
        Value columnName = record.get("column_name");

        QueryGroup result = new QueryGroup();

        result.setId(id);
        result.setQueryId(queryId);
        result.setTableName(tableName, fromTable);
        result.setColumnName(columnName);

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
    public String getTableName() {
        return tableName != null ? tableName : "";
    }

    public void setTableName(String tableName) {
        this.tableName = tableName != null ? tableName.trim() : "";
    }

    public void setTableName(Value value, @NotEmpty String fromTable) {
        String tableName = Optional.ofNullable(value)
                .map(Value::getString)
                .filter(Predicate.not(String::isBlank))
                .orElse(fromTable);
        setTableName(tableName);
    }

    @NotNull
    public String getColumnName() {
        return columnName != null ? columnName : "";
    }

    public void setColumnName(Value value) {
        String columnName = value != null ? value.getString() : null;
        setColumnName(columnName);
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName != null ? columnName.trim() : "";
    }

}
