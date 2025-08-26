package io.github.winter.database.query.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 连表
 *
 * @author changebooks@qq.com
 */
public final class QueryJoinEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 查询主键
     */
    private Integer queryId;

    /**
     * 表名
     */
    private String joinTable;

    /**
     * 连表方式
     */
    private Integer joinType;

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
