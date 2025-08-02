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
    private int joinType;

    /**
     * 连表条件
     */
    private List<On> joinOns;

    /**
     * 连表条件
     */
    public static final class On implements Serializable {
        /**
         * 左表名
         */
        private String leftTable;

        /**
         * 左字段名
         */
        private String leftColumn;

        /**
         * 右表名
         */
        private String rightTable;

        /**
         * 右字段名
         */
        private String rightColumn;

    }

}
