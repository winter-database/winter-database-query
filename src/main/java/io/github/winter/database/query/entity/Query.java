package io.github.winter.database.query.entity;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

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
    private String queryName;

    /**
     * 去重？
     */
    private boolean distinct;

    /**
     * 全字段？
     */
    private boolean asterisk;

    /**
     * 忽略参数名？
     */
    private boolean parameterName;

    /**
     * 表名
     */
    private String fromTable;

    /**
     * 开始行数
     */
    private long pageOffset;

    /**
     * 每页行数
     */
    private int pageLimit;

    /**
     * 描述
     */
    private String queryDescription;

    /**
     * 备注
     */
    private String queryRemark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getQueryName() {
        return queryName != null ? queryName : "";
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName != null ? queryName.trim() : "";
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isAsterisk() {
        return asterisk;
    }

    public void setAsterisk(boolean asterisk) {
        this.asterisk = asterisk;
    }

    public boolean isParameterName() {
        return parameterName;
    }

    public void setParameterName(boolean parameterName) {
        this.parameterName = parameterName;
    }

    @NotNull
    public String getFromTable() {
        return fromTable != null ? fromTable : "";
    }

    public void setFromTable(String fromTable) {
        this.fromTable = fromTable != null ? fromTable.trim() : "";
    }

    public long getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(long pageOffset) {
        this.pageOffset = pageOffset;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
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

}
