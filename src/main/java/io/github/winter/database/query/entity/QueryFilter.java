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
 * 条件
 *
 * @author changebooks@qq.com
 */
public final class QueryFilter implements Serializable {
    /**
     * 主键
     */
    private int id;

    /**
     * 查询主键
     */
    private int queryId;

    /**
     * 父条件主键
     */
    private int parentId;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 函数类型
     */
    private int funcType;

    /**
     * 条件类型
     */
    private int filterType;

    /**
     * 逻辑与或
     */
    private int logicalOperator;

    /**
     * 分组条件？
     */
    private boolean having;

    /**
     * Build Instance List
     *
     * @param list      [ [ Column Name : Column Value ] ]
     * @param fromTable Table Name
     * @return [ the {@link QueryFilter} instance ]
     */
    public static List<QueryFilter> newInstance(List<Map<String, Value>> list, @NotEmpty String fromTable) {
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
     * @return the {@link QueryFilter} instance
     */
    public static QueryFilter newInstance(Map<String, Value> record, @NotEmpty String fromTable) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value parentId = record.get("parent_id");
        Value tableName = record.get("table_name");
        Value columnName = record.get("column_name");
        Value funcType = record.get("func_type");
        Value filterType = record.get("filter_type");
        Value logicalOperator = record.get("logical_operator");
        Value isHaving = record.get("is_having");

        QueryFilter result = new QueryFilter();

        result.setId(id);
        result.setQueryId(queryId);
        result.setParentId(parentId);
        result.setTableName(tableName, fromTable);
        result.setColumnName(columnName);
        result.setFuncType(funcType);
        result.setFilterType(filterType);
        result.setLogicalOperator(logicalOperator);
        result.setHaving(isHaving);

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(Value value) {
        int parentId = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setParentId(parentId);
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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

    public int getFuncType() {
        return funcType;
    }

    public void setFuncType(Value value) {
        int funcType = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setFuncType(funcType);
    }

    public void setFuncType(int funcType) {
        this.funcType = funcType;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(Value value) {
        int filterType = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setFilterType(filterType);
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public int getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(Value value) {
        int logicalOperator = Optional.ofNullable(value).map(Value::getInteger).orElse(0);
        setLogicalOperator(logicalOperator);
    }

    public void setLogicalOperator(int logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public boolean isHaving() {
        return having;
    }

    public void setHaving(Value value) {
        Integer having = value != null ? value.getInteger() : null;
        boolean isHaving = BooleanCast.fromInt(having);
        setHaving(isHaving);
    }

    public void setHaving(boolean having) {
        this.having = having;
    }

}
