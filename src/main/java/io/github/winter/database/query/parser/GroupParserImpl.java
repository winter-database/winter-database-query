package io.github.winter.database.query.parser;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.sql.Preconditions;
import io.github.winter.boot.sql.Statement;
import io.github.winter.database.query.Group;
import io.github.winter.database.query.GroupParser;
import io.github.winter.database.query.HavingParser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author changebooks@qq.com
 */
public class GroupParserImpl implements GroupParser {
    /**
     * the {@link HavingParser} instance
     */
    private final HavingParser havingParser;

    public GroupParserImpl() {
        this.havingParser = new HavingParserImpl();
    }

    @Override
    public Statement parse(Group group) {
        if (group == null) {
            return null;
        }

        List<String> names = group.getNames();
        Statement statement = parseGroup(names);
        if (statement == null) {
            return null;
        }

        List<BaseFilter> filters = group.getFilters();
        Statement having = havingParser.parse(filters);

        statement.join(having);
        return statement;
    }

    /**
     * 解析分组
     *
     * @param names [ name ]
     * @return GROUP BY name, name
     */
    protected Statement parseGroup(List<String> names) {
        String sql = parse(names);
        if (sql.isBlank()) {
            return null;
        } else {
            return new Statement(sql);
        }
    }

    @Override
    public String parse(List<String> names) {
        if (names != null) {
            String sql = names.stream()
                    .peek(name -> Preconditions.requireNonNull(name, "name must not be null"))
                    .peek(name -> Preconditions.requireNonEmpty(name, "name must not be empty"))
                    .collect(Collectors.joining(", "));
            return prefixed(sql);
        } else {
            return "";
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

    public HavingParser getHavingParser() {
        return havingParser;
    }

}
