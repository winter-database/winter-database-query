package io.github.winter.database.query;

import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.sql.SqlParameter;
import io.github.winter.boot.sql.Statement;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 解析查询
 *
 * @author changebooks@qq.com
 */
public interface QueryParser {
    /**
     * 解析
     *
     * @param query the {@link Query} instance
     * @return the {@link SqlParameter} instance
     */
    SqlParameter parse(Query query);

    /**
     * 解析
     *
     * @param query  the {@link Query} instance
     * @param orders [ the {@link Order} instance ]
     * @param page   the {@link Page} instance
     * @return the {@link SqlParameter} instance
     */
    SqlParameter parse(Query query, List<Order> orders, Page page);

    /**
     * 连接字段名
     *
     * @param query the {@link Query} instance
     * @return name, COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    @NotNull
    String joinColumnNames(@NotNull Query query);

    /**
     * 连表
     *
     * @param statement the {@link Statement} instance
     * @param query     the {@link Query} instance
     */
    default void joinTable(@NotNull Statement statement, @NotNull Query query) {
        String sql = parseJoin(query);
        statement.joinSql(sql);
    }

    /**
     * 解析连表
     *
     * @param query the {@link Query} instance
     * @return LEFT JOIN table2 ON table1.column1 = table2.column2 LEFT JOIN table3 ON table1.column2 = table3.column3
     */
    String parseJoin(@NotNull Query query);

    /**
     * 连接条件
     *
     * @param statement the {@link Statement} instance
     * @param query     the {@link Query} instance
     */
    default void joinWhere(@NotNull Statement statement, @NotNull Query query) {
        Statement parsedWhere = parseWhere(query);
        statement.join(parsedWhere);
    }

    /**
     * 解析条件
     *
     * @param query the {@link Query} instance
     * @return the {@link Statement} instance
     */
    Statement parseWhere(@NotNull Query query);

    /**
     * 连接分组
     *
     * @param statement the {@link Statement} instance
     * @param query     the {@link Query} instance
     */
    default void joinGroup(@NotNull Statement statement, @NotNull Query query) {
        Statement parsedGroup = parseGroup(query);
        statement.join(parsedGroup);
    }

    /**
     * 解析分组
     *
     * @param query the {@link Query} instance
     * @return the {@link Statement} instance
     */
    Statement parseGroup(@NotNull Query query);

    /**
     * 连接排序
     *
     * @param statement the {@link Statement} instance
     * @param query     the {@link Query} instance
     * @param orders    [ the {@link Order} instance ]
     */
    default void joinOrder(@NotNull Statement statement, @NotNull Query query, List<Order> orders) {
        String sql = parseOrder(query, orders);
        statement.joinSql(sql);
    }

    /**
     * 解析排序
     *
     * @param query  the {@link Query} instance
     * @param orders [ the {@link Order} instance ]
     * @return ORDER BY name, name ASC, name DESC
     */
    String parseOrder(@NotNull Query query, List<Order> orders);

    /**
     * 解析排序
     *
     * @param query the {@link Query} instance
     * @return ORDER BY name, name ASC, name DESC
     */
    String parseOrder(@NotNull Query query);

    /**
     * 连接分页
     *
     * @param statement the {@link Statement} instance
     * @param query     the {@link Query} instance
     * @param page      the {@link Page} instance
     */
    default void joinPage(@NotNull Statement statement, @NotNull Query query, Page page) {
        String sql = parsePage(query, page);
        statement.joinSql(sql);
    }

    /**
     * 解析分页
     *
     * @param query the {@link Query} instance
     * @param page  the {@link Page} instance
     * @return LIMIT offset, limit
     */
    String parsePage(@NotNull Query query, Page page);

    /**
     * 解析分页
     *
     * @param query the {@link Query} instance
     * @return LIMIT offset, limit
     */
    String parsePage(@NotNull Query query);

}
