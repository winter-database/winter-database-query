package io.github.winter.database.query.parser;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.sql.*;
import io.github.winter.boot.sql.parser.OrderParserImpl;
import io.github.winter.boot.sql.parser.PageParserImpl;
import io.github.winter.boot.sql.parser.WhereParserImpl;
import io.github.winter.database.query.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

        String tableName = query.getTableName();
        Preconditions.requireNonEmpty(tableName, "tableName must not be empty");

        String columns = joinColumns(query);
        Preconditions.requireNonEmpty(columns, "columns must not be empty");

        boolean distinct = query.isDistinct();

        String sql = String.format
                (
                        "SELECT %s%s FROM %s",
                        distinct ? "DISTINCT " : "",
                        columns,
                        tableName
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
    public String joinColumns(Query query) {
        List<Column> columns = query.getColumns();
        return Optional.ofNullable(columns)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(Column::getSqlName)
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.joining(", "));
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
    public String parseOrder(Query query) {
        List<Order> orders = query.getOrders();
        return orderParser.parse(orders);
    }

    @Override
    public String parsePage(Query query) {
        Page page = query.getPage();
        return pageParser.parse(page);
    }

}
