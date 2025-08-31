package io.github.winter.database.query.reader;

import io.github.winter.boot.tuple.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 连表条件
 *
 * @author changebooks@qq.com
 */
public final class QueryJoinOn implements Serializable {
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

    /**
     * Build Instance List
     *
     * @param list [ [ Column Name : Column Value ] ]
     * @return [ the {@link QueryJoinOn} instance ]
     */
    public static List<QueryJoinOn> newInstances(List<Map<String, Value>> list) {
        if (list != null) {
            return list.stream()
                    .filter(Objects::nonNull)
                    .map(QueryJoinOn::newInstance)
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
     * @return the {@link QueryJoinOn} instance
     */
    public static QueryJoinOn newInstance(Map<String, Value> record) {
        if (record == null) {
            return null;
        }

        Value id = record.get("id");
        Value queryId = record.get("query_id");
        Value joinId = record.get("join_id");
        Value leftTable = record.get("left_table");
        Value leftColumn = record.get("left_column");
        Value rightTable = record.get("right_table");
        Value rightColumn = record.get("right_column");
        Value showPriority = record.get("show_priority");
        Value updateVersion = record.get("update_version");
        Value createDate = record.get("create_date");
        Value lastUpdate = record.get("last_update");

        QueryJoinOn result = new QueryJoinOn();

        result.setId(id);
        result.setQueryId(queryId);
        result.setJoinId(joinId);
        result.setLeftTable(leftTable);
        result.setLeftColumn(leftColumn);
        result.setRightTable(rightTable);
        result.setRightColumn(rightColumn);
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

    public Integer getJoinId() {
        return joinId;
    }

    public void setJoinId(Value value) {
        Integer joinId = value != null ? value.getInteger() : null;
        setJoinId(joinId);
    }

    public void setJoinId(Integer joinId) {
        this.joinId = joinId;
    }

    public String getLeftTable() {
        return leftTable;
    }

    public void setLeftTable(Value value) {
        String leftTable = value != null ? value.getString() : null;
        setLeftTable(leftTable);
    }

    public void setLeftTable(String leftTable) {
        this.leftTable = leftTable;
    }

    public String getLeftColumn() {
        return leftColumn;
    }

    public void setLeftColumn(Value value) {
        String leftColumn = value != null ? value.getString() : null;
        setLeftColumn(leftColumn);
    }

    public void setLeftColumn(String leftColumn) {
        this.leftColumn = leftColumn;
    }

    public String getRightTable() {
        return rightTable;
    }

    public void setRightTable(Value value) {
        String rightTable = value != null ? value.getString() : null;
        setRightTable(rightTable);
    }

    public void setRightTable(String rightTable) {
        this.rightTable = rightTable;
    }

    public String getRightColumn() {
        return rightColumn;
    }

    public void setRightColumn(Value value) {
        String rightColumn = value != null ? value.getString() : null;
        setRightColumn(rightColumn);
    }

    public void setRightColumn(String rightColumn) {
        this.rightColumn = rightColumn;
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
