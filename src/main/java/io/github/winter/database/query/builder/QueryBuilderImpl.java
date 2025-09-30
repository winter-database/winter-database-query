package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.database.query.*;
import io.github.winter.database.query.parser.QueryParserImpl;
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

    /**
     * the {@link ColumnBuilder} instance
     */
    private final ColumnBuilder columnBuilder;

    /**
     * the {@link QueryParser} instance
     */
    private final QueryParser queryParser;

    public QueryBuilderImpl(JdbcTemplate jdbcTemplate) {
        this.querySelect = new QuerySelect(jdbcTemplate);
        this.columnBuilder = new ColumnBuilderImpl();
        this.queryParser = new QueryParserImpl();
    }

    @Override
    public List<Integer> selectIds() {
        return null;
    }

    @Override
    public Query build(int id) {
        io.github.winter.database.query.entity.Query record = querySelect.selectQuery(id);
        if (record == null) {
            return null;
        }

        int queryId = record.getId();
        String queryName = record.getQueryName();
        boolean distinct = record.isDistinct();
        boolean asterisk = record.isAsterisk();
        boolean parameterName = record.isParameterName();
        String fromTable = record.getFromTable();
        long pageOffset = record.getPageOffset();
        int pageLimit = record.getPageLimit();
        String queryDescription = record.getQueryDescription();
        String queryRemark = record.getQueryRemark();

        return null;
    }

    @Override
    public List<Column> selectColumn(int queryId, String fromTable) {
        return null;
    }

    @Override
    public List<Join> selectJoin(int queryId) {
        return null;
    }

    @Override
    public List<Join.On> selectJoinOn(int queryId, int joinId) {
        return null;
    }

    @Override
    public Group selectGroup(int queryId, boolean isParameterName, String fromTable) {
        return null;
    }

    @Override
    public List<Order> selectOrder(int queryId, String fromTable) {
        return null;
    }

    @Override
    public List<BaseFilter> selectFilter(int isHaving, int queryId, int parentId, boolean isParameterName, String fromTable) {
        return null;
    }

    public QuerySelect getQuerySelect() {
        return querySelect;
    }

    public ColumnBuilder getColumnBuilder() {
        return columnBuilder;
    }

    public QueryParser getQueryParser() {
        return queryParser;
    }

}
