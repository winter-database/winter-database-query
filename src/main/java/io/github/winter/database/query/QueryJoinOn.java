package io.github.winter.database.query;

import java.io.Serializable;

/**
 * 连表条件
 *
 * @author changebooks@qq.com
 */
public class QueryJoinOn implements Serializable {
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
