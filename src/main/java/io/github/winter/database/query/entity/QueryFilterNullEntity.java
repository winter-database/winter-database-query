package io.github.winter.database.query.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 空
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterNullEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 查询主键
     */
    private Integer queryId;

    /**
     * 条件主键
     */
    private Integer filterId;

    /**
     * 取反？
     */
    private Boolean isNot;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date lastUpdate;

}
