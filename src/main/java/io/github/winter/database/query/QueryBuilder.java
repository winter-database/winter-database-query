package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
     * @param asterisk  全字段？
     * @param joins     [ the {@link Join} instance ]
     * @return [ the {@link Column} instance ]
     */
    default List<Column> selectColumn(int queryId, @NotEmpty String fromTable, boolean asterisk, List<Join> joins) {
        if (asterisk) {
            List<String> asteriskTables = selectAsteriskTables(fromTable, joins);
            return selectColumn(queryId, fromTable, asteriskTables);
        } else {
            return selectColumn(queryId, fromTable);
        }
    }

    /**
     * 字段 + 全字段
     *
     * @param queryId        查询主键
     * @param fromTable      表名
     * @param asteriskTables 全字段表名
     * @return [ the {@link Column} instance ]
     */
    default List<Column> selectColumn(int queryId, @NotEmpty String fromTable, List<String> asteriskTables) {
        List<Column> result = selectColumn(queryId, fromTable);

        if (asteriskTables == null || asteriskTables.isEmpty()) {
            return result;
        }

        if (result == null) {
            result = new ArrayList<>();
        }

        Set<String> asNames = result.stream()
                .filter(Objects::nonNull)
                .map(Column::getAsName)
                .collect(Collectors.toSet());

        for (String tableName : asteriskTables) {
            if (tableName == null || tableName.isEmpty()) {
                continue;
            }

            List<Column> columns = selectAsterisk(tableName);
            if (columns == null) {
                continue;
            }

            for (Column column : columns) {
                if (column == null) {
                    continue;
                }

                String asName = column.getAsName();
                if (asName.isEmpty()) {
                    continue;
                }

                if (asNames.contains(asName)) {
                    continue;
                }

                asNames.add(asName);
                result.add(column);
            }
        }

        return result;
    }

    /**
     * 字段
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ the {@link Column} instance ]
     */
    List<Column> selectColumn(int queryId, @NotEmpty String fromTable);

    /**
     * 全字段
     *
     * @param fromTable 表名
     * @return [ the {@link Column} instance ]
     */
    List<Column> selectAsterisk(@NotEmpty String fromTable);

    /**
     * 全字段表名
     *
     * @param fromTable 表名
     * @param joins     [ the {@link Join} instance ]
     * @return [ 表名 ]
     */
    @NotNull
    default List<String> selectAsteriskTables(@NotEmpty String fromTable, List<Join> joins) {
        List<String> result = new ArrayList<>();
        result.add(fromTable);

        if (joins == null) {
            return result;
        }

        for (Join join : joins) {
            if (join == null) {
                continue;
            }

            String subQuery = join.getSubQuery();
            if (subQuery.isEmpty()) {
                continue;
            }

            String tableName = join.getTableName();
            if (tableName.isEmpty()) {
                continue;
            }

            if (result.contains(tableName)) {
                continue;
            }

            result.add(tableName);
        }

        return result;
    }

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
     * @param queryId   查询主键
     * @param subQuery  子查询？
     * @param fromTable 表名
     * @return the {@link Group} instance
     */
    Group selectGroup(int queryId, boolean subQuery, @NotEmpty String fromTable);

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
     * @param isHaving  分组条件？
     * @param queryId   查询主键
     * @param parentId  父条件主键
     * @param subQuery  子查询？
     * @param fromTable 表名
     * @return [ the {@link BaseFilter} instance ]
     */
    List<BaseFilter> selectFilter(int isHaving, int queryId, int parentId, boolean subQuery, @NotEmpty String fromTable);

}
