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
 * 查询
 *
 * @author changebooks@qq.com
 */
public final class QueryDto implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String queryName;

    /**
     * 去重？
     */
    private Boolean isDistinct;

    /**
     * 全字段？
     */
    private Boolean isAsterisk;

    /**
     * 表名
     */
    private String fromTable;

    /**
     * 开始行数
     */
    private Long pageOffset;

    /**
     * 每页行数
     */
    private Integer pageLimit;

    /**
     * 描述
     */
    private String queryDescription;

    /**
     * 备注
     */
    private String queryRemark;

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
     * @return [ the {@link QueryDto} instance ]
     */
    public static List<QueryDto> newInstance(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryDto::newInstance)
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
     * @return the {@link QueryDto} instance
     */
    public static QueryDto newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryName = record.get("query_name");
        Value isDistinct = record.get("is_distinct");
        Value isAsterisk = record.get("is_asterisk");
        Value fromTable = record.get("from_table");
        Value pageOffset = record.get("page_offset");
        Value pageLimit = record.get("page_limit");
        Value queryDescription = record.get("query_description");
        Value queryRemark = record.get("query_remark");
        Value showPriority = record.get("show_priority");
        Value updateVersion = record.get("update_version");
        Value createDate = record.get("create_date");
        Value lastUpdate = record.get("last_update");

        QueryDto result = new QueryDto();

        result.setId(id);
        result.setQueryName(queryName);
        result.setDistinct(isDistinct);
        result.setAsterisk(isAsterisk);
        result.setFromTable(fromTable);
        result.setPageOffset(pageOffset);
        result.setPageLimit(pageLimit);
        result.setQueryDescription(queryDescription);
        result.setQueryRemark(queryRemark);
        result.setShowPriority(showPriority);
        result.setUpdateVersion(updateVersion);
        result.setCreateDate(createDate);
        result.setLastUpdate(lastUpdate);

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

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(Value value) {
        String queryName = value != null ? value.getString() : null;
        setQueryName(queryName);
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public boolean getDistinct() {
        return isDistinct != null ? isDistinct : false;
    }

    public void setDistinct(Value value) {
        Integer distinct = value != null ? value.getInteger() : null;
        Boolean isDistinct = BooleanCast.fromInt(distinct);
        setDistinct(isDistinct);
    }

    public void setDistinct(Boolean distinct) {
        isDistinct = distinct;
    }

    public boolean getAsterisk() {
        return isAsterisk != null ? isAsterisk : false;
    }

    public void setAsterisk(Value value) {
        Integer asterisk = value != null ? value.getInteger() : null;
        Boolean isAsterisk = BooleanCast.fromInt(asterisk);
        setAsterisk(isAsterisk);
    }

    public void setAsterisk(Boolean asterisk) {
        isAsterisk = asterisk;
    }

    @NotNull
    public String getFromTable() {
        return fromTable != null ? fromTable : "";
    }

    public void setFromTable(Value value) {
        String fromTable = value != null ? value.getString() : null;
        setFromTable(fromTable);
    }

    public void setFromTable(String fromTable) {
        this.fromTable = fromTable != null ? fromTable.trim() : "";
    }

    public Long getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Value value) {
        Long pageOffset = value != null ? value.getLong() : null;
        setPageOffset(pageOffset);
    }

    public void setPageOffset(Long pageOffset) {
        this.pageOffset = pageOffset;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Value value) {
        Integer pageLimit = value != null ? value.getInteger() : null;
        setPageLimit(pageLimit);
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public String getQueryDescription() {
        return queryDescription;
    }

    public void setQueryDescription(Value value) {
        String queryDescription = value != null ? value.getString() : null;
        setQueryDescription(queryDescription);
    }

    public void setQueryDescription(String queryDescription) {
        this.queryDescription = queryDescription;
    }

    public String getQueryRemark() {
        return queryRemark;
    }

    public void setQueryRemark(Value value) {
        String queryRemark = value != null ? value.getString() : null;
        setQueryRemark(queryRemark);
    }

    public void setQueryRemark(String queryRemark) {
        this.queryRemark = queryRemark;
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
