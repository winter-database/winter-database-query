package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
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
    List<Column> selectColumn(int queryId, @NotEmpty String fromTable);

    /**
     * 全部表名
     *
     * @param fromTable 表名
     * @param joins     [ the {@link Join} instance ]
     * @return [ 表名 ]
     */
    @NotNull
    List<String> selectTableName(@NotEmpty String fromTable, List<Join> joins);

    /**
     * 连表
     *
     * @param queryId 查询主键
     * @return [ the {@link Join} instance ]
     */
    List<Join> selectJoin(int queryId);

    /**
     * 连表条件
     *
     * @param queryId 查询主键
     * @param joinId  连表主键
     * @return [ the {@link Join.On} instance ]
     */
    List<Join.On> selectJoinOn(int queryId, int joinId);

    /**
     * 分组
     *
     * @param queryId         查询主键
     * @param isParameterName 忽略参数名？
     * @param fromTable       表名
     * @return the {@link Group} instance
     */
    Group selectGroup(int queryId, boolean isParameterName, @NotEmpty String fromTable);

    /**
     * 排序
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ the {@link Order} instance ]
     */
    List<Order> selectOrder(int queryId, @NotEmpty String fromTable);

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
    List<BaseFilter> selectFilter(int isHaving, int queryId, int parentId, boolean isParameterName, @NotEmpty String fromTable);

    /**
     * 参数名
     *
     * @param isParameterName 忽略参数名？
     * @param parameterName   自定义的参数名
     * @param filterName      条件名
     * @return 忽略参数名，或自定义的参数名，或条件名
     */
    @NotNull
    default String getParameterName(boolean isParameterName, String parameterName, String filterName) {
        if (isParameterName) {
            if (parameterName == null || parameterName.isEmpty()) {
                return filterName == null ? "" : filterName;
            } else {
                return parameterName;
            }
        } else {
            return "";
        }
    }

}
