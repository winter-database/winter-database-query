package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Page;

import java.util.List;

/**
 * 创建查询
 *
 * @author changebooks@qq.com
 */
public interface QueryBuilder {
    /**
     * 创建
     *
     * @param id 主键
     * @return the {@link Query} instance
     */
    Query build(int id);

    /**
     * 字段
     *
     * @param query the {@link Query} instance
     */
    void setColumn(Query query, boolean isAsterisk);

    /**
     * 字段列表
     */
    Class<?> getClass();

    /**
     * 连表
     *
     * @param query the {@link Query} instance
     */
    void setJoin(Query query);

    /**
     * 条件
     *
     * @param query the {@link Query} instance
     */
    void setFilter(Query query);

    /**
     * 分组
     *
     * @param query the {@link Query} instance
     */
    void setGroup(Query query);

    /**
     * 排序
     *
     * @param query the {@link Query} instance
     */
    void setOrder(Query query);

    /**
     * 分页
     *
     * @param query the {@link Query} instance
     */
    void setPage(Query query);

    /**
     * 条件列表
     *
     * @param isHaving 分组条件？
     * @param queryId  查询主键
     * @param parentId 父条件主键
     * @return [ the {@link BaseFilter} instance ]
     */
    List<BaseFilter> getFilter(int isHaving, int queryId, int parentId);

    /**
     * 分页
     *
     * @param offset 开始行数
     * @param limit  每页行数
     * @return the {@link Page} instance
     */
    Page getPage(Long offset, Integer limit);

}
