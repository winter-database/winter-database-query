package io.github.winter.database.query.parser;

import io.github.winter.database.query.JoinParser;
import io.github.winter.database.query.constant.JoinType;

/**
 * @author changebooks@qq.com
 */
public class JoinParserImpl implements JoinParser {

    @Override
    public String parse(int type, String name) {
        return switch (type) {
            case JoinType.INNER -> "JOIN " + name;
            case JoinType.LEFT -> "LEFT JOIN " + name;
            case JoinType.RIGHT -> "RIGHT JOIN " + name;
            default -> throw new RuntimeException("unsupported join type");
        };
    }

}
