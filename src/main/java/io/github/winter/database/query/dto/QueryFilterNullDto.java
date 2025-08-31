package io.github.winter.database.query.dto;

import io.github.winter.boot.tuple.Value;
import io.github.winter.database.template.Template;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 空
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterNullDto implements Serializable {
    /**
     * 表名
     */
    public static final String TABLE_NAME = "xquery_filter_null";

    /**
     * 主键
     */
    private Integer id;

    /**
     * 查询主键
     */
    private Integer queryId;

    /**
     * 条件主键
     */
    private Integer filterId;

    /**
     * 取反？
     */
    private Boolean isNot;

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
     * @param filterId 条件主键
     * @return [ the {@link QueryFilterNullDto} instance ]
     */
    public static List<QueryFilterNullDto> readInstances(@NotNull Template template, int queryId, int filterId) {
        List<Map<String, Value>> list = DtoUtils.selectList(template, TABLE_NAME, queryId, filterId);
        return newInstances(list);
    }

    /**
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryFilterNullDto} instance ]
     */
    public static List<QueryFilterNullDto> newInstances(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryFilterNullDto::newInstance)
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
     * @return the {@link QueryFilterNullDto} instance
     */
    public static QueryFilterNullDto newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value filterId = record.get("filter_id");
        Value isNot = record.get("is_not");
        Value updateVersion = record.get("update_version");
        Value createDate = record.get("create_date");
        Value lastUpdate = record.get("last_update");

        QueryFilterNullDto result = new QueryFilterNullDto();

        result.setId(id);
        result.setQueryId(queryId);
        result.setFilterId(filterId);
        result.setNot(isNot);
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

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Value value) {
        Integer filterId = value != null ? value.getInteger() : null;
        setFilterId(filterId);
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    public Boolean getNot() {
        return isNot;
    }

    public void setNot(Value value) {
        Integer not = value != null ? value.getInteger() : null;
        Boolean isNot = DtoUtils.toBoolean(not);
        setNot(isNot);
    }

    public void setNot(Boolean not) {
        isNot = not;
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
