package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.sql.Preconditions;
import io.github.winter.database.query.*;
import io.github.winter.database.query.entity.BooleanCast;
import io.github.winter.database.query.parser.QueryParserImpl;
import jakarta.validation.constraints.NotEmpty;
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
        String fromTable = record.getFromTable();
        Preconditions.requireNonEmpty(fromTable, "fromTable must not be empty, queryId: " + queryId);

        String queryName = record.getQueryName();
        boolean distinct = record.isDistinct();
        boolean asterisk = record.isAsterisk();
        boolean parameterName = record.isParameterName();
        long pageOffset = record.getPageOffset();
        int pageLimit = record.getPageLimit();
        String queryDescription = record.getQueryDescription();
        String queryRemark = record.getQueryRemark();

        List<Join> joins = buildJoin(queryId);
        List<Column> columns = buildColumn(queryId, asterisk, fromTable, joins);
        List<BaseFilter> filters = buildFilter(BooleanCast.FALSE, queryId, 0, parameterName, fromTable);
        Group group = buildGroup(queryId, parameterName, fromTable);
        List<Order> orders = buildOrder(queryId, fromTable);
        Page page = buildPage(pageOffset, pageLimit);

        Query result = new Query();

        result.setId(id);
        result.setName(queryName);
        result.setDistinct(distinct);
        result.setColumns(columns);
        result.setTableName(fromTable);
        result.setJoins(joins);
        result.setFilters(filters);
        result.setGroup(group);
        result.setOrders(orders);
        result.setPage(page);
        result.setDescription(queryDescription);
        result.setRemark(queryRemark);

        return result;
    }

    protected List<Column> buildColumn(int queryId, boolean asterisk, @NotEmpty String fromTable, List<Join> joins) {
        
    }

    @Override
    public List<Column> buildColumn(int queryId, String fromTable) {
        return null;
    }

    @Override
    public List<Join> buildJoin(int queryId) {
        return null;
    }

    @Override
    public List<Join.On> buildJoinOn(int queryId, int joinId) {
        return null;
    }

    @Override
    public Group buildGroup(int queryId, boolean isParameterName, String fromTable) {
        return null;
    }

    @Override
    public List<Order> buildOrder(int queryId, String fromTable) {
        return null;
    }

    @Override
    public Page buildPage(long offset, int limit) {
        return null;
    }

    @Override
    public List<BaseFilter> buildFilter(int isHaving, int queryId, int parentId, boolean isParameterName, String fromTable) {
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
