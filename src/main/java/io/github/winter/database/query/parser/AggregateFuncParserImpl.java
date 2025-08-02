package io.github.winter.database.query.parser;

import io.github.winter.database.query.AggregateFunc;
import io.github.winter.database.query.AggregateFuncParser;

/**
 * @author changebooks@qq.com
 */
public class AggregateFuncParserImpl implements AggregateFuncParser {

    @Override
    public String parse(int aggregateFunc, String name) {
        return switch (aggregateFunc) {
            case AggregateFunc.COUNT -> "COUNT(*)";
            case AggregateFunc.SUM -> "SUM(" + name + ")";
            case AggregateFunc.MAX -> "MAX(" + name + ")";
            case AggregateFunc.MIN -> "MIN(" + name + ")";
            case AggregateFunc.AVG -> "AVG(" + name + ")";
            default -> name;
        };
    }

}
