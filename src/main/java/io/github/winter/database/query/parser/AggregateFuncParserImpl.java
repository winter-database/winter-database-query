package io.github.winter.database.query.parser;

import io.github.winter.database.query.AggregateFuncEnum;
import io.github.winter.database.query.AggregateFuncParser;

/**
 * @author changebooks@qq.com
 */
public class AggregateFuncParserImpl implements AggregateFuncParser {

    @Override
    public String parse(int aggregateFunc, String tableName, String columnName) {
        String name = joinName(tableName, columnName);
        return parse(aggregateFunc, name);
    }

    @Override
    public String parse(int aggregateFunc, String name) {
        return switch (aggregateFunc) {
            case AggregateFuncEnum.COUNT -> "COUNT(*)";
            case AggregateFuncEnum.SUM -> "SUM(" + name + ")";
            case AggregateFuncEnum.MAX -> "MAX(" + name + ")";
            case AggregateFuncEnum.MIN -> "MIN(" + name + ")";
            case AggregateFuncEnum.AVG -> "AVG(" + name + ")";
            default -> name;
        };
    }

    @Override
    public String joinName(String tableName, String columnName) {
        if (columnName == null || columnName.isEmpty()) {
            return "";
        }

        if (tableName == null || tableName.isEmpty()) {
            return columnName;
        } else {
            return tableName + "." + columnName;
        }
    }

}
