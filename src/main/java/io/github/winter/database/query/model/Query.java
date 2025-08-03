package io.github.winter.database.query.model;

import java.io.Serializable;
import java.util.List;

/**
 * 查询
 *
 * @author changebooks@qq.com
 */
public final class Query implements Serializable {
    /**
     * 主键
     */
    private int id;

    /**
     * 名称
     */
    private String name;

    /**
     * 去重？
     */
    private boolean distinct;

    /**
     * 字段列表
     */
    private List<QueryColumn> columns;

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
     * 连表列表
     */
    private List<QueryJoin> joins;

    /**
     * 条件列表
     */
    private List<QueryFilter> filters;

    /**
     * 分组列表
     */
    private List<QueryGroup> groups;

    /**
     * 排序列表
     */
    private List<QueryOrder> orders;

    /**
     * 分页方式
     */
    private int pageType;

    /**
     * 每页行数
     */
    private int pageSize;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private int showPriority;

}
