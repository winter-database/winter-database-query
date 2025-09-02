package io.github.winter.database.query.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 连表
 *
 * @author changebooks@qq.com
 */
public final class QueryJoinDto implements Serializable {
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
    private String joinTable;

    /**
     * 连表方式
     */
    private Integer joinType;

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
    public String getJoinTable() {
        return joinTable != null ? joinTable : "";
    }

    public void setJoinTable(String joinTable) {
        this.joinTable = joinTable != null ? joinTable.trim() : "";
    }

    public int getJoinType() {
        return joinType != null ? joinType : 0;
    }

    public void setJoinType(Integer joinType) {
        this.joinType = joinType;
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
