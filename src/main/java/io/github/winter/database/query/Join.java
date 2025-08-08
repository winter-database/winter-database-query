package io.github.winter.database.query;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 连表
 *
 * @author changebooks@qq.com
 */
public class Join implements Serializable {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 子查询名
     */
    private String subQueryName;

    /**
     * 连表方式
     */
    private int type;

    /**
     * 条件列表
     */
    private List<On> filters;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName != null ? tableName.trim() : "";
    }

    public Query getSubQuery() {
        return subQuery;
    }

    public void setSubQuery(Query subQuery) {
        this.subQuery = subQuery;
    }

    public String getSubQueryName() {
        return subQueryName;
    }

    public void setSubQueryName(String subQueryName) {
        this.subQueryName = subQueryName != null ? subQueryName.trim() : "";
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<On> getFilters() {
        return filters;
    }

    public void setFilters(List<On> filters) {
        this.filters = Optional.ofNullable(filters)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    /**
     * 连表条件
     *
     * @author changebooks@qq.com
     */
    public static class On implements Serializable {
        /**
         * 左表名 + 左表字段名
         */
        private String leftName;

        /**
         * 右表名 + 右表字段名
         */
        private String rightName;

        public String getLeftName() {
            return leftName;
        }

        public void setLeftName(String leftName) {
            this.leftName = leftName != null ? leftName.trim() : "";
        }

        public String getRightName() {
            return rightName;
        }

        public void setRightName(String rightName) {
            this.rightName = rightName != null ? rightName.trim() : "";
        }

    }

}
