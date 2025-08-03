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

    }

}
