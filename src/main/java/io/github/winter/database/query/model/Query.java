package io.github.winter.database.query.model;

import java.io.Serializable;
import java.util.List;

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
    private String name;

    /**
     * 去重？
     */
    private boolean distinct;

    /**
     * 字段列表
     */
    private List<QueryColumn> columns;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 子查询别名
     */
    private String subQueryName;

    /**
     * 连表列表
     */
    private List<QueryJoin> joins;

    /**
     * 条件列表
     */
    private List<QueryFilter> filters;

    /**
     * 分组列表
     */
    private List<QueryGroup> groups;

    /**
     * 排序列表
     */
    private List<QueryOrder> orders;

    /**
     * 分页方式
     */
    private int pageType;

    /**
     * 每页行数
     */
    private int pageSize;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private int showPriority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public List<QueryColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<QueryColumn> columns) {
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Query getSubQuery() {
        return subQuery;
    }

    public void setSubQuery(Query subQuery) {
        this.subQuery = subQuery;
    }

    public String getSubQueryName() {
        return subQueryName;
    }

    public void setSubQueryName(String subQueryName) {
        this.subQueryName = subQueryName;
    }

    public List<QueryJoin> getJoins() {
        return joins;
    }

    public void setJoins(List<QueryJoin> joins) {
        this.joins = joins;
    }

    public List<QueryFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<QueryFilter> filters) {
        this.filters = filters;
    }

    public List<QueryGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<QueryGroup> groups) {
        this.groups = groups;
    }

    public List<QueryOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<QueryOrder> orders) {
        this.orders = orders;
    }

    public int getPageType() {
        return pageType;
    }

    public void setPageType(int pageType) {
        this.pageType = pageType;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getShowPriority() {
        return showPriority;
    }

    public void setShowPriority(int showPriority) {
        this.showPriority = showPriority;
    }

}
