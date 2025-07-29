package io.github.winter.database.query.parser;

import io.github.winter.database.query.GroupParser;

import java.util.List;

/**
 * @author changebooks@qq.com
 */
public class GroupParserImpl implements GroupParser {

    @Override
    public String parse(List<String> list) {
        if (list == null) {
            return null;
        }

        String sql = String.join(", ", list);
        if (sql.isBlank()) {
            return null;
        } else {
            return prefixed(sql);
        }
    }

    @Override
    public String prefixed(String sql) {
        if (sql == null || sql.isBlank()) {
            return "";
        } else {
            return "GROUP BY " + sql;
        }
    }

}
