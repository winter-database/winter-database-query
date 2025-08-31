package io.github.winter.database.query.dto;

import io.github.winter.boot.tuple.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 排序
 *
 * @author changebooks@qq.com
 */
public final class QueryOrder implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 查询主键
     */
    private Integer queryId;

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
    private Integer funcType;

    /**
     * 排序方式
     */
    private Integer orderType;

    /**
     * 排序
     */
    private Integer showPriority;

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
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryOrder} instance ]
     */
    public static List<QueryOrder> newInstances(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryOrder::newInstance)
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
     * @return the {@link QueryOrder} instance
     */
    public static QueryOrder newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value tableName = record.get("table_name");
        Value columnName = record.get("column_name");
        Value funcType = record.get("func_type");
        Value orderType = record.get("order_type");
        Value showPriority = record.get("show_priority");
        Value updateVersion = record.get("update_version");
        Value createDate = record.get("create_date");
        Value lastUpdate = record.get("last_update");

        QueryOrder result = new QueryOrder();

        result.setId(id);
        result.setQueryId(queryId);
        result.setTableName(tableName);
        result.setColumnName(columnName);
        result.setFuncType(funcType);
        result.setOrderType(orderType);
        result.setShowPriority(showPriority);
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(Value value) {
        String tableName = value != null ? value.getString() : null;
        setTableName(tableName);
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(Value value) {
        String columnName = value != null ? value.getString() : null;
        setColumnName(columnName);
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getFuncType() {
        return funcType;
    }

    public void setFuncType(Value value) {
        Integer funcType = value != null ? value.getInteger() : null;
        setFuncType(funcType);
    }

    public void setFuncType(Integer funcType) {
        this.funcType = funcType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Value value) {
        Integer orderType = value != null ? value.getInteger() : null;
        setOrderType(orderType);
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getShowPriority() {
        return showPriority;
    }

    public void setShowPriority(Value value) {
        Integer showPriority = value != null ? value.getInteger() : null;
        setShowPriority(showPriority);
    }

    public void setShowPriority(Integer showPriority) {
        this.showPriority = showPriority;
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
