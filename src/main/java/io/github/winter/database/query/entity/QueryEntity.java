package io.github.winter.database.query.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询
 *
 * @author changebooks@qq.com
 */
public final class QueryEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String queryName;

    /**
     * 去重？
     */
    private Boolean isDistinct;

    /**
     * 全字段？
     */
    private Boolean isAsterisk;

    /**
     * 表名
     */
    private String fromTable;

    /**
     * 开始行数
     */
    private Long pageOffset;

    /**
     * 每页行数
     */
    private Integer pageLimit;

    /**
     * 描述
     */
    private String queryDescription;

    /**
     * 备注
     */
    private String queryRemark;

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
