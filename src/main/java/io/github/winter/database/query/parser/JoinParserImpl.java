package io.github.winter.database.query.parser;

import io.github.winter.database.query.JoinEnum;
import io.github.winter.database.query.JoinParser;

/**
 * @author changebooks@qq.com
 */
public class JoinParserImpl implements JoinParser {

    @Override
    public String parse(String joinTable, int joinEnum) {
        return switch (joinEnum) {
            case JoinEnum.INNER -> "INNER JOIN " + joinTable;
            case JoinEnum.LEFT -> "LEFT JOIN " + joinTable;
            case JoinEnum.RIGHT -> "RIGHT JOIN " + joinTable;
            default -> "JOIN " + joinTable;
        };
    }

}
