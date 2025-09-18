package io.github.winter.database.query.parser;

import io.github.winter.database.query.SubQueryParser;

/**
 * @author changebooks@qq.com
 */
public class SubQueryParserImpl implements SubQueryParser {

    @Override
    public String parse(String subQuery, String tableName) {
        if (tableName == null || tableName.isEmpty()) {
            return "";
        }

        if (subQuery == null || subQuery.isEmpty()) {
            return tableName;
        }

        return "(" + subQuery + ") AS " + tableName;
    }

}
