package io.github.winter.database.query.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 连表条件
 *
 * @author changebooks@qq.com
 */
public final class QueryJoinOnDto implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 查询主键
     */
    private Integer queryId;

    /**
     * 连表主键
     */
    private Integer joinId;

    /**
     * 左表名
     */
    private String leftTable;

    /**
     * 左表字段名
     */
    private String leftColumn;

    /**
     * 右表名
     */
    private String rightTable;

    /**
     * 右表字段名
     */
    private String rightColumn;

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

    public int getJoinId() {
        return joinId != null ? joinId : 0;
    }

    public void setJoinId(Integer joinId) {
        this.joinId = joinId;
    }

    @NotNull
    public String getLeftTable() {
        return leftTable != null ? leftTable : "";
    }

    public void setLeftTable(String leftTable) {
        this.leftTable = leftTable != null ? leftTable.trim() : "";
    }

    @NotNull
    public String getLeftColumn() {
        return leftColumn != null ? leftColumn : "";
    }

    public void setLeftColumn(String leftColumn) {
        this.leftColumn = leftColumn != null ? leftColumn.trim() : "";
    }

    @NotNull
    public String getRightTable() {
        return rightTable != null ? rightTable : "";
    }

    public void setRightTable(String rightTable) {
        this.rightTable = rightTable != null ? rightTable.trim() : "";
    }

    @NotNull
    public String getRightColumn() {
        return rightColumn != null ? rightColumn : "";
    }

    public void setRightColumn(String rightColumn) {
        this.rightColumn = rightColumn != null ? rightColumn.trim() : "";
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
