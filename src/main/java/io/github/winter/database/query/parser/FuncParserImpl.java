package io.github.winter.database.query.parser;

import io.github.winter.database.query.FuncParser;
import io.github.winter.database.query.FuncType;
import io.github.winter.database.query.QueryUtils;

/**
 * @author changebooks@qq.com
 */
public class FuncParserImpl implements FuncParser {

    @Override
    public String parse(int func, String tableName, String columnName) {
        String name = QueryUtils.joinName(tableName, columnName);
        return parse(func, name);
    }

    @Override
    public String parse(int func, String name) {
        return switch (func) {
            case FuncType.COUNT -> "COUNT(*)";
            case FuncType.SUM -> "SUM(" + name + ")";
            case FuncType.MAX -> "MAX(" + name + ")";
            case FuncType.MIN -> "MIN(" + name + ")";
            case FuncType.AVG -> "AVG(" + name + ")";
            default -> name;
        };
    }

}
