package io.github.winter.database.query.parser;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.sql.*;
import io.github.winter.boot.sql.parser.OrderParserImpl;
import io.github.winter.boot.sql.parser.PageParserImpl;
import io.github.winter.boot.sql.parser.WhereParserImpl;
import io.github.winter.database.query.*;

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
        return parse(query, null, null);
    }

    @Override
    public SqlParameter parse(Query query, List<Order> orders, Page page) {
        if (query == null) {
            return null;
        }

        String tableName = query.getTableName();
        Preconditions.requireNonEmpty(tableName, "tableName must not be empty");

        String columnNames = joinColumnNames(query);
        Preconditions.requireNonEmpty(columnNames, "columnNames must not be empty");

        boolean distinct = query.isDistinct();

        String sql = String.format
                (
                        "SELECT %s%s FROM %s",
                        distinct ? "DISTINCT " : "",
                        columnNames,
                        tableName
                );

        Statement statement = new Statement(sql);

        joinTable(statement, query);
        joinWhere(statement, query);
        joinGroup(statement, query);
        joinOrder(statement, query, orders);
        joinPage(statement, query, page);

        return SqlParameterParser.parse(statement);
    }

    @Override
    public String joinColumnNames(Query query) {
        List<String> columnNames = query.getColumnNames();
        if (columnNames != null) {
            return String.join(", ", columnNames);
        } else {
            return "";
        }
    }

    @Override
    public String parseJoin(Query query) {
        List<Join> joins = query.getJoins();
        return joinParser.parse(joins);
    }

    @Override
    public Statement parseWhere(Query query) {
        List<BaseFilter> filters = query.getFilters();
        return whereParser.parse(filters);
    }

    @Override
    public Statement parseGroup(Query query) {
        Group group = query.getGroup();
        return groupParser.parse(group);
    }

    @Override
    public String parseOrder(Query query, List<Order> orders) {
        if (orders != null) {
            return orderParser.parse(orders);
        } else {
            return parseOrder(query);
        }
    }

    @Override
    public String parseOrder(Query query) {
        List<Order> orders = query.getOrders();
        return orderParser.parse(orders);
    }

    @Override
    public String parsePage(Query query, Page page) {
        if (page != null) {
            return pageParser.parse(page);
        } else {
            return parsePage(query);
        }
    }

    @Override
    public String parsePage(Query query) {
        Page page = query.getPage();
        return pageParser.parse(page);
    }

}
