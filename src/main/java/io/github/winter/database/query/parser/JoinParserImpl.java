package io.github.winter.database.query.parser;

import io.github.winter.boot.sql.LogicalOperator;
import io.github.winter.boot.sql.Preconditions;
import io.github.winter.database.query.Join;
import io.github.winter.database.query.JoinParser;
import io.github.winter.database.query.JoinType;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author changebooks@qq.com
 */
public class JoinParserImpl implements JoinParser {

    @Override
    public String parse(List<Join> joins) {
        if (joins != null) {
            return joins.stream()
                    .map(this::parse)
                    .filter(Objects::nonNull)
                    .filter(Predicate.not(String::isEmpty))
                    .collect(Collectors.joining(" "));
        } else {
            return null;
        }
    }

    @Override
    public String parse(Join join) {
        if (join == null) {
            return null;
        }

        String tableName = join.getTableName();
        Preconditions.requireNonEmpty(tableName, "tableName must not be empty");

        List<Join.On> filters = join.getFilters();
        Preconditions.requireNonNull(filters, "filters must not be null, tableName: " + tableName);

        String joinOn = parseFilter(filters);
        Preconditions.requireNonNull(joinOn, "joinOn must not be null, tableName: " + tableName);
        Preconditions.requireNonEmpty(joinOn, "joinOn must not be empty, tableName: " + tableName);

        int type = join.getType();
        String joinTable = parse(type, tableName);

        return joinTable + " ON " + joinOn;
    }

    @Override
    public String parse(int type, String tableName) {
        return switch (type) {
            case JoinType.INNER -> "JOIN " + tableName;
            case JoinType.LEFT -> "LEFT JOIN " + tableName;
            case JoinType.RIGHT -> "RIGHT JOIN " + tableName;
            default -> throw new RuntimeException("unsupported join type, type: " + type + ", tableName: " + tableName);
        };
    }

    @Override
    public String parseFilter(List<Join.On> filters) {
        if (filters != null) {
            return filters.stream()
                    .map(this::parseFilter)
                    .filter(Objects::nonNull)
                    .filter(Predicate.not(String::isEmpty))
                    .collect(Collectors.joining(LogicalOperator.AND_WITH_WHITESPACE));
        } else {
            return null;
        }
    }

    @Override
    public String parseFilter(Join.On filter) {
        if (filter == null) {
            return null;
        }

        String leftName = filter.getLeftName();
        if (leftName.isEmpty()) {
            return "";
        }

        String rightName = filter.getRightName();
        if (rightName.isEmpty()) {
            return "";
        }

        return leftName + " = " + rightName;
    }

}
