package io.github.winter.database.query.dto;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.template.Template;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.*;

/**
 * 条件
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterDto implements Serializable {
    /**
     * 表名
     */
    public static final String TABLE_NAME = "xquery_filter";

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

    /**
     * Read Instance List
     *
     * @param template the {@link Template} instance
     * @param queryId  查询主键
     * @param parentId 父条件主键
     * @param isHaving 分组条件？
     * @return [ the {@link QueryFilterDto} instance ]
     */
    public static List<QueryFilterDto> readInstances(@NotNull Template template, int queryId, int parentId, int isHaving) {
        List<BaseFilter> filters = new ArrayList<>();

        filters.add(DtoUtils.newFilter("internal_recycle", 0));
        filters.add(DtoUtils.newFilter("query_id", queryId));
        filters.add(DtoUtils.newFilter("parent_id", parentId));
        filters.add(DtoUtils.newFilter("is_having", isHaving));

        List<Map<String, Value>> list = DtoUtils.selectList(template, TABLE_NAME, filters);
        return newInstances(list);
    }

    /**
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryFilterDto} instance ]
     */
    public static List<QueryFilterDto> newInstances(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryFilterDto::newInstance)
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
     * @return the {@link QueryFilterDto} instance
     */
    public static QueryFilterDto newInstance(Map<String, Value> record) {
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
        Value showPriority = record.get("show_priority");
        Value updateVersion = record.get("update_version");
        Value createDate = record.get("create_date");
        Value lastUpdate = record.get("last_update");

        QueryFilterDto result = new QueryFilterDto();

        result.setId(id);
        result.setQueryId(queryId);
        result.setParentId(parentId);
        result.setTableName(tableName);
        result.setColumnName(columnName);
        result.setFuncType(funcType);
        result.setFilterType(filterType);
        result.setLogicalOperator(logicalOperator);
        result.setHaving(isHaving);
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Value value) {
        Integer parentId = value != null ? value.getInteger() : null;
        setParentId(parentId);
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Value value) {
        Integer filterType = value != null ? value.getInteger() : null;
        setFilterType(filterType);
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(Value value) {
        Integer logicalOperator = value != null ? value.getInteger() : null;
        setLogicalOperator(logicalOperator);
    }

    public void setLogicalOperator(Integer logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public Boolean getHaving() {
        return isHaving;
    }

    public void setHaving(Value value) {
        Integer having = value != null ? value.getInteger() : null;
        Boolean isHaving = DtoUtils.toBoolean(having);
        setHaving(isHaving);
    }

    public void setHaving(Boolean having) {
        isHaving = having;
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
