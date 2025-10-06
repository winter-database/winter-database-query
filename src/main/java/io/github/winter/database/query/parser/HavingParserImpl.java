package io.github.winter.database.query.parser;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.sql.Statement;
import io.github.winter.boot.sql.StatementParser;
import io.github.winter.boot.sql.parser.FilterParserImpl;
import io.github.winter.database.query.HavingParser;

import java.util.List;

/**
 * @author changebooks@qq.com
 */
public class HavingParserImpl implements HavingParser {
    /**
     * the {@link StatementParser} instance
     */
    private final StatementParser statementParser;

    public HavingParserImpl() {
        this.statementParser = new StatementParser(new FilterParserImpl());
    }

    @Override
    public Statement parse(List<BaseFilter> filters) {
        StatementParser statementParser = getStatementParser();
        Statement statement = statementParser.parse(filters);
        return prefixed(statement);
    }

    @Override
    public Statement parse(BaseFilter filter) {
        StatementParser statementParser = getStatementParser();
        Statement statement = statementParser.parse(filter);
        return prefixed(statement);
    }

    @Override
    public Statement prefixed(Statement statement) {
        if (statement != null) {
            String sql = statement.getSql();
            String prefixedSql = prefixed(sql);
            statement.setSql(prefixedSql);
            return statement;
        } else {
            return null;
        }
    }

    @Override
    public String prefixed(String sql) {
        if (sql == null || sql.isBlank()) {
            return "";
        } else {
            return "HAVING " + sql;
        }
    }

    public StatementParser getStatementParser() {
        return statementParser;
    }

}
