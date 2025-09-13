package io.github.winter.database.query.builder;

import io.github.winter.database.query.Query;
import io.github.winter.database.query.QueryBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author changebooks@qq.com
 */
public class QueryBuilderImpl implements QueryBuilder {
    /**
     * the {@link QuerySelect} instance
     */
    private final QuerySelect querySelect;

    public QueryBuilderImpl(JdbcTemplate jdbcTemplate) {
        this.querySelect = new QuerySelect(jdbcTemplate);
    }

    @Override
    public List<Query> buildAll() {
        return null;
    }

    @Override
    public Query build(int id) {
        return null;
    }

    public QuerySelect getQuerySelect() {
        return querySelect;
    }

}
