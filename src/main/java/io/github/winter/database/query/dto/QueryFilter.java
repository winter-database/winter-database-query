package io.github.winter.database.query.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 条件
 *
 * @author changebooks@qq.com
 */
public final class QueryFilter implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 查询主键
     */
    private Integer queryId;

    /**
     * 父条件主键
     */
    private Integer parentId;

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
     * 条件类型
     */
    private Integer filterType;

    /**
     * 逻辑与或
     */
    private Integer logicalOperator;

    /**
     * 分组条件？
     */
    private Boolean isHaving;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQueryId() {
        return queryId;
    }

    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getFuncType() {
        return funcType;
    }

    public void setFuncType(Integer funcType) {
        this.funcType = funcType;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(Integer logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public Boolean getHaving() {
        return isHaving;
    }

    public void setHaving(Boolean having) {
        isHaving = having;
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
