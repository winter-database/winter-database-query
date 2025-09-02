package io.github.winter.database.query.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 排序
 *
 * @author changebooks@qq.com
 */
public final class QueryOrderDto implements Serializable {
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

    public int getId() {
        return id != null ? id : 0;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQueryId() {
        return queryId != null ? queryId : 0;
    }

    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }

    @NotNull
    public String getTableName() {
        return tableName != null ? tableName : "";
    }

    public void setTableName(String tableName) {
        this.tableName = tableName != null ? tableName.trim() : "";
    }

    @NotNull
    public String getColumnName() {
        return columnName != null ? columnName : "";
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName != null ? columnName.trim() : "";
    }

    public int getFuncType() {
        return funcType != null ? funcType : 0;
    }

    public void setFuncType(Integer funcType) {
        this.funcType = funcType;
    }

    public int getOrderType() {
        return orderType != null ? orderType : 0;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getShowPriority() {
        return showPriority;
    }

    public void setShowPriority(Integer showPriority) {
        this.showPriority = showPriority;
    }

    public Integer getUpdateVersion() {
        return updateVersion;
    }

    public void setUpdateVersion(Integer updateVersion) {
        this.updateVersion = updateVersion;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
