package io.github.winter.database.query.dto;

import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.BooleanCast;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 在列表中
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterInDto implements Serializable {
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
     * 子查询主键
     */
    private Integer subQueryId;

    /**
     * 参数名
     */
    private String parameterName;

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

    public int getFilterId() {
        return filterId != null ? filterId : 0;
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
        Boolean isNot = BooleanCast.fromInt(not);
        setNot(isNot);
    }

    public void setNot(Boolean not) {
        isNot = not;
    }

    public int getSubQueryId() {
        return subQueryId != null ? subQueryId : 0;
    }

    public void setSubQueryId(Value value) {
        Integer subQueryId = value != null ? value.getInteger() : null;
        setSubQueryId(subQueryId);
    }

    public void setSubQueryId(Integer subQueryId) {
        this.subQueryId = subQueryId;
    }

    @NotNull
    public String getParameterName() {
        return parameterName != null ? parameterName : "";
    }

    public void setParameterName(Value value) {
        String parameterName = value != null ? value.getString() : null;
        setParameterName(parameterName);
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName != null ? parameterName.trim() : "";
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
