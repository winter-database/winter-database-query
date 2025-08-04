package io.github.winter.database.query.model;

import java.io.Serializable;
import java.util.List;

/**
 * 连表
 *
 * @author changebooks@qq.com
 */
public final class QueryJoin implements Serializable {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 子查询别名
     */
    private String subQueryName;

    /**
     * 连表方式
     */
    private int type;

    /**
     * 条件列表
     */
    private List<On> ons;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
        this.subQueryName = subQueryName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<On> getOns() {
        return ons;
    }

    public void setOns(List<On> ons) {
        this.ons = ons;
    }

    /**
     * 连表条件
     */
    public static final class On implements Serializable {
        /**
         * 左表，表名 + 字段名
         */
        private String leftName;

        /**
         * 右表，表名 + 字段名
         */
        private String rightName;

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

        public String getLeftName() {
            return leftName;
        }

        public void setLeftName(String leftName) {
            this.leftName = leftName;
        }

        public String getRightName() {
            return rightName;
        }

        public void setRightName(String rightName) {
            this.rightName = rightName;
        }

        public String getLeftTable() {
            return leftTable;
        }

        public void setLeftTable(String leftTable) {
            this.leftTable = leftTable;
        }

        public String getLeftColumn() {
            return leftColumn;
        }

        public void setLeftColumn(String leftColumn) {
            this.leftColumn = leftColumn;
        }

        public String getRightTable() {
            return rightTable;
        }

        public void setRightTable(String rightTable) {
            this.rightTable = rightTable;
        }

        public String getRightColumn() {
            return rightColumn;
        }

        public void setRightColumn(String rightColumn) {
            this.rightColumn = rightColumn;
        }

    }

}
