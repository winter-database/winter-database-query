package io.github.winter.database.query.parser;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.sql.*;
import io.github.winter.boot.sql.parser.OrderParserImpl;
import io.github.winter.boot.sql.parser.PageParserImpl;
import io.github.winter.boot.sql.parser.WhereParserImpl;
import io.github.winter.database.query.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * @author changebooks@qq.com
 */
public class QueryParserImpl implements QueryParser {
    /**
     * the {@link JoinParser} instance
     */
    private final JoinParser joinParser;

    /**
     * the {@link WhereParser} instance
     */
    private final WhereParser whereParser;

    /**
     * the {@link GroupParser} instance
     */
    private final GroupParser groupParser;

    /**
     * the {@link OrderParser} instance
     */
    private final OrderParser orderParser;

    /**
     * the {@link PageParser} instance
     */
    private final PageParser pageParser;

    public QueryParserImpl() {
        this.joinParser = new JoinParserImpl();
        this.whereParser = new WhereParserImpl();
        this.groupParser = new GroupParserImpl();
        this.orderParser = new OrderParserImpl();
        this.pageParser = new PageParserImpl();
    }

    @Override
    public SqlParameter parse(Query query) {
        if (query == null) {
            return null;
        }

        boolean distinct = query.isDistinct();
        String columns = joinColumns(query);
        String table = query.getTableName();

        String sql = String.format
                (
                        "SELECT %s%s FROM %s",
                        distinct ? "DISTINCT " : "",
                        columns,
                        table
                );

        Statement statement = new Statement(sql);

        joinTable(statement, query);
        joinWhere(statement, query);
        joinGroup(statement, query);
        joinOrder(statement, query);
        joinPage(statement, query);

        return SqlParameterParser.parse(statement);
    }

    @Override
    public String joinColumns(@NotNull Query query) {
        List<String> columns = query.getColumns();
        if (columns != null) {
            return String.join(", ", columns);
        } else {
            return null;
        }
    }

    @Override
    public String parseJoin(@NotNull Query query) {
        List<Join> joins = query.getJoins();
        return joinParser.parse(joins);
    }

    @Override
    public Statement parseWhere(@NotNull Query query) {
        List<BaseFilter> filters = query.getFilters();
        return whereParser.parse(filters);
    }

    @Override
    public Statement parseGroup(@NotNull Query query) {
        Group group = query.getGroup();
        return groupParser.parse(group);
    }

    @Override
    public String parseOrder(@NotNull Query query) {
        List<Order> orders = query.getOrders();
        return orderParser.parse(orders);
    }

    @Override
    public String parsePage(@NotNull Query query) {
        Page page = query.getPage();
        return pageParser.parse(page);
    }

}
