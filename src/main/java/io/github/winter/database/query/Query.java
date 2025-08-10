package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 查询
 *
 * @author changebooks@qq.com
 */
public class Query implements Serializable {
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
    private List<String> columns;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 子查询名
     */
    private String subQueryName;

    /**
     * 连表列表
     */
    private List<Join> joins;

    /**
     * 条件列表
     */
    private List<BaseFilter> filters;

    /**
     * 分组
     */
    private Group group;

    /**
     * 排序列表
     */
    private List<Order> orders;

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
    private int priority;

    /**
     * [ 字段名 : 字段类型 ]
     */
    private Map<String, Class<?>> valueTypes;

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
        this.name = name != null ? name.trim() : "";
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = Optional.ofNullable(columns)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .distinct()
                .toList();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName != null ? tableName.trim() : "";
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
        this.subQueryName = subQueryName != null ? subQueryName.trim() : "";
    }

    public List<Join> getJoins() {
        return joins;
    }

    public void setJoins(List<Join> joins) {
        this.joins = Optional.ofNullable(joins)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    public List<BaseFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<BaseFilter> filters) {
        this.filters = Optional.ofNullable(filters)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = Optional.ofNullable(orders)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .toList();
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Map<String, Class<?>> getValueTypes() {
        return valueTypes;
    }

    public void setValueTypes(Map<String, Class<?>> valueTypes) {
        this.valueTypes = Optional.ofNullable(valueTypes)
                .orElse(Collections.emptyMap())
                .entrySet()
                .stream()
                .filter(Objects::nonNull)
                .filter(x -> x.getKey() != null)
                .filter(x -> x.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
