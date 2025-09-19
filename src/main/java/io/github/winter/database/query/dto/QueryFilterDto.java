package io.github.winter.database.query.dto;

import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.BooleanCast;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 条件
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterDto implements Serializable {
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
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryFilterDto} instance ]
     */
    public static List<QueryFilterDto> newInstance(List<Map<String, Value>> list) {
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


        QueryFilterDto result = new QueryFilterDto();


        return result;
    }

    public int getId() {
        return id != null ? id : 0;
    }

    public void setId(Value value) {
        Integer id = value != null ? value.getInteger() : null;
        setId(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQueryId() {
        return queryId != null ? queryId : 0;
    }

    public void setQueryId(Value value) {
        Integer queryId = value != null ? value.getInteger() : null;
        setQueryId(queryId);
    }

    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }

    public int getParentId() {
        return parentId != null ? parentId : 0;
    }

    public void setParentId(Value value) {
        Integer parentId = value != null ? value.getInteger() : null;
        setParentId(parentId);
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @NotNull
    public String getTableName() {
        return tableName != null ? tableName : "";
    }

    public void setTableName(Value value) {
        String tableName = value != null ? value.getString() : null;
        setTableName(tableName);
    }

    public void setTableName(String tableName) {
        this.tableName = tableName != null ? tableName.trim() : "";
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
        return funcType != null ? funcType : 0;
    }

    public void setFuncType(Value value) {
        Integer funcType = value != null ? value.getInteger() : null;
        setFuncType(funcType);
    }

    public void setFuncType(Integer funcType) {
        this.funcType = funcType;
    }

    public int getFilterType() {
        return filterType != null ? filterType : 0;
    }

    public void setFilterType(Value value) {
        Integer filterType = value != null ? value.getInteger() : null;
        setFilterType(filterType);
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public int getLogicalOperator() {
        return logicalOperator != null ? logicalOperator : 0;
    }

    public void setLogicalOperator(Value value) {
        Integer logicalOperator = value != null ? value.getInteger() : null;
        setLogicalOperator(logicalOperator);
    }

    public void setLogicalOperator(Integer logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public boolean getHaving() {
        return isHaving != null ? isHaving : false;
    }

    public void setHaving(Value value) {
        Integer having = value != null ? value.getInteger() : null;
        Boolean isHaving = BooleanCast.fromInt(having);
        setHaving(isHaving);
    }

    public void setHaving(Boolean having) {
        isHaving = having;
    }

    public int getShowPriority() {
        return showPriority != null ? showPriority : 0;
    }

    public void setShowPriority(Value value) {
        Integer showPriority = value != null ? value.getInteger() : null;
        setShowPriority(showPriority);
    }

    public void setShowPriority(Integer showPriority) {
        this.showPriority = showPriority;
    }

    public int getUpdateVersion() {
        return updateVersion != null ? updateVersion : 0;
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
