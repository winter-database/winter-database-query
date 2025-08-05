package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.sql.SqlParameter;
import io.github.winter.boot.sql.Statement;
import io.github.winter.database.query.model.Query;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 解析命令
 *
 * @author changebooks@qq.com
 */
public interface SelectParser {
    /**
     * SELECT DISTINCT table1.column1, COUNT(1), SUM(table1.column1), MAX(table2.column2), MIN(table2.column2), AVG(table3.column3)
     * FROM table1 LEFT JOIN table2 ON table1.column1 = table2.column2 LEFT JOIN table3 ON table1.column1 = table3.column3
     * WHERE table1.column1 = :parameterName AND table2.column2 IS NOT NULL OR (table3.column3 >= :parameterName AND table3.column3 <= :parameterName)
     * GROUP BY table1.column1, table2.column2 HAVING SUM(table1.column1) >= :parameterName AND MAX(table2.column2) <= :parameterName
     * ORDER BY SUM(table1.column1), MAX(table2.column2) ASC
     * LIMIT offset, limit
     *
     * @param query the {@link Query} instance
     * @return the {@link SqlParameter} instance
     */
    @NotNull
    SqlParameter parse(@NotNull Query query);

    /**
     * 连接条件
     *
     * @param statement the {@link Statement} instance
     * @param filters   [ the {@link BaseFilter} instance ]
     */
    default void joinWhere(@NotNull Statement statement, List<BaseFilter> filters) {
        Statement parsedWhere = parseWhere(filters);
        statement.join(parsedWhere);
    }

    /**
     * 连接条件
     *
     * @param sql   SELECT column, column FROM table
     * @param where column = ?
     * @return SELECT column, column FROM table WHERE column = ?
     */
    default String joinWhere(@NotNull String sql, String where) {
        String prefixedWhere = prefixedWhere(where);
        if (prefixedWhere == null || prefixedWhere.isBlank()) {
            return sql;
        } else {
            return sql + " " + prefixedWhere;
        }
    }

    /**
     * 解析条件
     *
     * @param filters [ the {@link BaseFilter} instance ]
     * @return the {@link Statement} instance
     */
    Statement parseWhere(List<BaseFilter> filters);

    /**
     * 解析条件
     *
     * @param filter the {@link BaseFilter} instance
     * @return the {@link Statement} instance
     */
    Statement parseWhere(BaseFilter filter);

    /**
     * 连接条件前缀
     *
     * @param sql column = ?
     * @return WHERE column = ?
     */
    String prefixedWhere(String sql);

    /**
     * 连接排序
     *
     * @param statement the {@link Statement} instance
     * @param orders    [ the {@link Order} instance ]
     */
    default void joinOrder(@NotNull Statement statement, List<Order> orders) {
        String sql = parseOrder(orders);
        statement.joinSql(sql);
    }

    /**
     * 解析排序
     *
     * @param orders [ the {@link Order} instance ]
     * @return ORDER BY name, name ASC, name DESC
     */
    String parseOrder(List<Order> orders);

    /**
     * 解析排序
     *
     * @param order the {@link Order} instance
     * @return ORDER BY name, ORDER BY name ASC, ORDER BY name DESC
     */
    String parseOrder(Order order);

    /**
     * 连接分页
     *
     * @param statement the {@link Statement} instance
     * @param page      the {@link Page} instance
     */
    default void joinPage(@NotNull Statement statement, Page page) {
        String sql = parsePage(page);
        statement.joinSql(sql);
    }

    /**
     * 解析分页
     *
     * @param page the {@link Page} instance
     * @return LIMIT offset, limit
     */
    String parsePage(Page page);

    /**
     * 连接分页首页
     *
     * @param statement the {@link Statement} instance
     */
    default void joinPageFirst(@NotNull Statement statement) {
        String sql = parsePageFirst();
        statement.joinSql(sql);
    }

    /**
     * 解析分页首页
     *
     * @return LIMIT 1
     */
    String parsePageFirst();

}
