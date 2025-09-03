package io.github.winter.database.query.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

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

    public int getId() {
        return id != null ? id : 0;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public boolean getDistinct() {
        return isDistinct != null ? isDistinct : false;
    }

    public void setDistinct(Boolean distinct) {
        isDistinct = distinct;
    }

    public boolean getAsterisk() {
        return isAsterisk != null ? isAsterisk : false;
    }

    public void setAsterisk(Boolean asterisk) {
        isAsterisk = asterisk;
    }

    @NotNull
    public String getFromTable() {
        return fromTable != null ? fromTable : "";
    }

    public void setFromTable(String fromTable) {
        this.fromTable = fromTable != null ? fromTable.trim() : "";
    }

    public Long getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Long pageOffset) {
        this.pageOffset = pageOffset;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public String getQueryDescription() {
        return queryDescription;
    }

    public void setQueryDescription(String queryDescription) {
        this.queryDescription = queryDescription;
    }

    public String getQueryRemark() {
        return queryRemark;
    }

    public void setQueryRemark(String queryRemark) {
        this.queryRemark = queryRemark;
    }

    public int getShowPriority() {
        return showPriority != null ? showPriority : 0;
    }

    public void setShowPriority(Integer showPriority) {
        this.showPriority = showPriority;
    }

    public int getUpdateVersion() {
        return updateVersion != null ? updateVersion : 0;
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
