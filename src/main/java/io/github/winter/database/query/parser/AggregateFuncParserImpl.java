package io.github.winter.database.query.parser;

import io.github.winter.database.query.AggregateFuncEnum;
import io.github.winter.database.query.AggregateFuncParser;

/**
 * @author changebooks@qq.com
 */
public class AggregateFuncParserImpl implements AggregateFuncParser {

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

}
