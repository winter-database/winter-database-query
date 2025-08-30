package io.github.winter.database.query.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 连表
 *
 * @author changebooks@qq.com
 */
public final class QueryJoin implements Serializable {
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

    public String getJoinTable() {
        return joinTable;
    }

    public void setJoinTable(String joinTable) {
        this.joinTable = joinTable;
    }

    public Integer getJoinType() {
        return joinType;
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
