package io.github.winter.database.query.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 连表条件
 *
 * @author changebooks@qq.com
 */
public final class QueryJoinOnEntity implements Serializable {
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
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date lastUpdate;

}
