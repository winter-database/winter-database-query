package io.github.winter.database.query.entity;

import io.github.winter.boot.tuple.Value;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 连表条件
 *
 * @author changebooks@qq.com
 */
public final class QueryJoinOn implements Serializable {
    /**
     * 主键
     */
    private int id;

    /**
     * 查询主键
     */
    private int queryId;

    /**
     * 连表主键
     */
    private int joinId;

    /**
     * 左表名
     */
    private String leftTable;

    /**
     * 左表字段名
     */
    private String leftColumn;

    /**
     * 右表名
     */
    private String rightTable;

    /**
     * 右表字段名
     */
    private String rightColumn;

    /**
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryJoinOn} instance ]
     */
    public static List<QueryJoinOn> newInstance(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryJoinOn::newInstance)
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
     * @return the {@link QueryJoinOn} instance
     */
    public static QueryJoinOn newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value joinId = record.get("join_id");
        Value leftTable = record.get("left_table");
        Value leftColumn = record.get("left_column");
        Value rightTable = record.get("right_table");
        Value rightColumn = record.get("right_column");

        QueryJoinOn result = new QueryJoinOn();

        result.setId(id);
        result.setQueryId(queryId);
        result.setJoinId(joinId);
        result.setLeftTable(leftTable);
        result.setLeftColumn(leftColumn);
        result.setRightTable(rightTable);
        result.setRightColumn(rightColumn);

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

    public int getJoinId() {
        return joinId;
    }

    public void setJoinId(Value value) {
        int joinId = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setJoinId(joinId);
    }

    public void setJoinId(int joinId) {
        this.joinId = joinId;
    }

    @NotNull
    public String getLeftTable() {
        return leftTable != null ? leftTable : "";
    }

    public void setLeftTable(Value value) {
        String leftTable = value != null ? value.getString() : null;
        setLeftTable(leftTable);
    }

    public void setLeftTable(String leftTable) {
        this.leftTable = leftTable != null ? leftTable.trim() : "";
    }

    @NotNull
    public String getLeftColumn() {
        return leftColumn != null ? leftColumn : "";
    }

    public void setLeftColumn(Value value) {
        String leftColumn = value != null ? value.getString() : null;
        setLeftColumn(leftColumn);
    }

    public void setLeftColumn(String leftColumn) {
        this.leftColumn = leftColumn != null ? leftColumn.trim() : "";
    }

    @NotNull
    public String getRightTable() {
        return rightTable != null ? rightTable : "";
    }

    public void setRightTable(Value value) {
        String rightTable = value != null ? value.getString() : null;
        setRightTable(rightTable);
    }

    public void setRightTable(String rightTable) {
        this.rightTable = rightTable != null ? rightTable.trim() : "";
    }

    @NotNull
    public String getRightColumn() {
        return rightColumn != null ? rightColumn : "";
    }

    public void setRightColumn(Value value) {
        String rightColumn = value != null ? value.getString() : null;
        setRightColumn(rightColumn);
    }

    public void setRightColumn(String rightColumn) {
        this.rightColumn = rightColumn != null ? rightColumn.trim() : "";
    }

}
