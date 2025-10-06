package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
    private List<Column> columns;

    /**
     * 字段名列表
     */
    private List<String> columnNames;

    /**
     * [ 字段名 : 字段类型 ]
     */
    private Map<String, Class<?>> valueTypes;

    /**
     * 表名
     */
    private String tableName;

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
     * 分页
     */
    private Page page;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name != null ? name : "";
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

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> list) {
        if (list == null) {
            return;
        }

        List<Column> columns = new ArrayList<>();
        List<String> columnNames = new ArrayList<>();
        Map<String, Class<?>> valueTypes = new HashMap<>();

        for (Column column : list) {
            if (column == null) {
                continue;
            }

            columns.add(column);

            String asName = column.getAsName();
            String fieldName = column.getFieldName();
            String columnName = Stream.of(asName, fieldName)
                    .filter(Objects::nonNull)
                    .filter(Predicate.not(String::isEmpty))
                    .findFirst()
                    .orElse("");
            if (columnName.isEmpty()) {
                continue;
            }

            Class<?> valueType = column.getValueType();
            if (valueType == null) {
                continue;
            }

            columnNames.add(columnName);
            valueTypes.put(columnName, valueType);
        }

        this.columns = columns;
        this.columnNames = columnNames;
        this.valueTypes = valueTypes;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public Map<String, Class<?>> getValueTypes() {
        return valueTypes;
    }

    @NotNull
    public String getTableName() {
        return tableName != null ? tableName : "";
    }

    public void setTableName(String tableName) {
        this.tableName = tableName != null ? tableName.trim() : "";
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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
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

}
