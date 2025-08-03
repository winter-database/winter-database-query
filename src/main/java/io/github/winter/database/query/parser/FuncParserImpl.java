package io.github.winter.database.query.parser;

import io.github.winter.database.query.FuncParser;
import io.github.winter.database.query.constant.FuncType;

/**
 * @author changebooks@qq.com
 */
public class FuncParserImpl implements FuncParser {

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
