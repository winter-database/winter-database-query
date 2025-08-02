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
     * 连表
     */
    private List<QueryJoin> joins;

    /**
     * 条件
     */
    private List<QueryFilter> where;

    /**
     * 分组
     */
    private List<QueryGroup> groups;

    /**
     * 分组条件
     */
    private List<QueryFilter> having;

    /**
     * 排序
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

}
