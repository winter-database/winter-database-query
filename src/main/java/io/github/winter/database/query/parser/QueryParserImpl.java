package io.github.winter.database.query.parser;

import io.github.winter.boot.sql.OrderParser;
import io.github.winter.boot.sql.PageParser;
import io.github.winter.boot.sql.WhereParser;
import io.github.winter.database.query.GroupParser;
import io.github.winter.database.query.JoinParser;
import io.github.winter.database.query.Query;
import io.github.winter.database.query.QueryParser;
import jakarta.validation.constraints.NotNull;

/**
 * @author changebooks@qq.com
 */
public class QueryParserImpl implements QueryParser {
    /**
     * the {@link JoinParser} instance
     */
    private final JoinParser joinParser;

    /**
     * the {@link WhereParser} instance
     */
    private final WhereParser whereParser;

    /**
     * the {@link GroupParser} instance
     */
    private final GroupParser groupParser;

    /**
     * the {@link OrderParser} instance
     */
    private final OrderParser orderParser;

    /**
     * the {@link PageParser} instance
     */
    private final PageParser pageParser;

    /**
     * 连接字段
     *
     * @param query the {@link Query} instance
     * @return name, COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    String joinColumns(@NotNull Query query);

}
