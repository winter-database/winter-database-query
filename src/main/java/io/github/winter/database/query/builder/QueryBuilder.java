package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import io.github.winter.database.query.Column;
import io.github.winter.database.query.Group;
import io.github.winter.database.query.Join;
import io.github.winter.database.query.Query;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 创建查询
 *
 * @author changebooks@qq.com
 */
public interface QueryBuilder {
    /**
     * 全部主键
     *
     * @return 主键列表
     */
    @NotNull
    List<Integer> selectIds();

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
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ the {@link Column} instance ]
     */
    List<Column> buildColumn(int queryId, @NotEmpty String fromTable);

    /**
     * 连表
     *
     * @param queryId 查询主键
     * @return [ the {@link Join} instance ]
     */
    List<Join> buildJoin(int queryId);

    /**
     * 连表条件
     *
     * @param queryId 查询主键
     * @param joinId  连表主键
     * @return [ the {@link Join.On} instance ]
     */
    List<Join.On> buildJoinOn(int queryId, int joinId);

    /**
     * 分组
     *
     * @param queryId         查询主键
     * @param isParameterName 忽略参数名？
     * @param fromTable       表名
     * @return the {@link Group} instance
     */
    Group buildGroup(int queryId, boolean isParameterName, @NotEmpty String fromTable);

    /**
     * 排序
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ the {@link Order} instance ]
     */
    List<Order> buildOrder(int queryId, @NotEmpty String fromTable);

    /**
     * 分页
     *
     * @param offset 开始行数
     * @param limit  每页行数
     * @return the {@link Page} instance
     */
    Page buildPage(long offset, int limit);

    /**
     * 条件
     *
     * @param isHaving        分组条件？
     * @param queryId         查询主键
     * @param parentId        父条件主键
     * @param isParameterName 忽略参数名？
     * @param fromTable       表名
     * @return [ the {@link BaseFilter} instance ]
     */
    @NotNull
    List<BaseFilter> buildFilter(int isHaving, int queryId, int parentId, boolean isParameterName, @NotEmpty String fromTable);

}
