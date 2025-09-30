package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.sql.Preconditions;
import io.github.winter.database.query.Query;
import io.github.winter.database.query.*;
import io.github.winter.database.query.entity.*;
import io.github.winter.database.query.parser.QueryParserImpl;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
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
        List<Integer> result = new ArrayList<>();

        List<io.github.winter.database.query.entity.Query> list = querySelect.selectQueryAll();
        if (list == null) {
            return result;
        }

        for (io.github.winter.database.query.entity.Query record : list) {
            if (record == null) {
                continue;
            }

            int id = record.getId();
            if (id > 0) {
                result.add(id);
            }
        }

        return result;
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

    /**
     * 字段
     *
     * @param queryId   查询主键
     * @param asterisk  全字段？
     * @param fromTable 表名
     * @param joins     [ the {@link Join} instance ]
     * @return [ the {@link Column} instance ]
     */
    protected List<Column> buildColumn(int queryId, boolean asterisk, @NotEmpty String fromTable, List<Join> joins) {
        List<Column> columns = buildColumn(queryId, fromTable);
        if (!asterisk) {
            return columns;
        }

        List<String> tableNames = NameUtils.getTableName(fromTable, joins);
        List<Column> asteriskColumns = columnBuilder.buildAsterisk(tableNames);
        if (asteriskColumns == null || asteriskColumns.isEmpty()) {
            return columns;
        }

        if (columns == null || columns.isEmpty()) {
            return asteriskColumns;
        }

        columns.addAll(asteriskColumns);
        return columns;
    }

    @Override
    public List<Column> buildColumn(int queryId, String fromTable) {
        List<QueryColumn> list = querySelect.selectColumn(queryId, fromTable);
        if (list == null) {
            return null;
        }

        List<Column> result = new ArrayList<>();

        for (QueryColumn record : list) {
            if (record == null) {
                continue;
            }

            int funcType = record.getFuncType();
            String tableName = record.getTableName();
            String columnName = record.getColumnName();
            String asName = record.getAsName();

            Column column = columnBuilder.build(funcType, tableName, columnName, asName);
            result.add(column);
        }

        return result;
    }

    @Override
    public List<Join> buildJoin(int queryId) {
        List<QueryJoin> list = querySelect.selectJoin(queryId);
        if (list == null) {
            return null;
        }

        List<Join> result = new ArrayList<>();

        for (QueryJoin record : list) {
            if (record == null) {
                continue;
            }

            String joinTable = record.getJoinTable();
            Preconditions.requireNonEmpty(joinTable, "joinTable must not be empty, queryId: " + queryId);

            int joinType = record.getJoinType();

            int joinId = record.getId();
            List<Join.On> filters = buildJoinOn(queryId, joinId);
            Preconditions.requireNonNull(filters, "filters must not be null, queryId: " + queryId + ", joinId: " + joinId);
            Preconditions.requireNonEmpty(filters, "filters must not be empty, queryId: " + queryId + ", joinId: " + joinId);

            Join join = new Join();

            join.setTableName(joinTable);
            join.setType(joinType);
            join.setFilters(filters);

            result.add(join);
        }

        return result;
    }

    @Override
    public List<Join.On> buildJoinOn(int queryId, int joinId) {
        List<QueryJoinOn> list = querySelect.selectJoinOn(queryId, joinId);
        if (list == null) {
            return null;
        }

        List<Join.On> result = new ArrayList<>();

        for (QueryJoinOn record : list) {
            if (record == null) {
                continue;
            }

            String leftTable = record.getLeftTable();
            String leftColumn = record.getLeftColumn();
            String rightTable = record.getRightTable();
            String rightColumn = record.getRightColumn();

            String leftName = NameUtils.joinName(leftTable, leftColumn);
            String rightName = NameUtils.joinName(rightTable, rightColumn);

            Join.On filter = new Join.On();

            filter.setLeftName(leftName);
            filter.setRightName(rightName);

            result.add(filter);
        }

        return result;
    }

    @Override
    public Group buildGroup(int queryId, boolean isParameterName, String fromTable) {
        List<QueryGroup> list = querySelect.selectGroup(queryId, fromTable);
        if (list == null) {
            return null;
        }

        List<String> groupNames = new ArrayList<>();

        for (QueryGroup record : list) {
            if (record == null) {
                continue;
            }

            String tableName = record.getTableName();
            String columnName = record.getColumnName();
            String groupName = NameUtils.joinName(tableName, columnName);
            groupNames.add(groupName);
        }

        List<BaseFilter> filters = buildFilter(BooleanCast.TRUE, queryId, 0, isParameterName, fromTable);

        Group result = new Group();

        result.setNames(groupNames);
        result.setFilters(filters);

        return result;
    }

    @Override
    public List<Order> buildOrder(int queryId, String fromTable) {
        List<QueryOrder> list = querySelect.selectOrder(queryId, fromTable);
        if (list == null) {
            return null;
        }

        List<Order> result = new ArrayList<>();

        for (QueryOrder record : list) {
            if (record == null) {
                continue;
            }

            int funcType = record.getFuncType();
            String tableName = record.getTableName();
            String columnName = record.getColumnName();
            String orderName = columnBuilder.joinFunc(funcType, tableName, columnName);

            int orderType = record.getOrderType();
            Boolean isDesc = OrderType.isDesc(orderType);

            Order order = new Order();

            order.setName(orderName);
            order.setDesc(isDesc);

            result.add(order);
        }

        return result;
    }

    @Override
    public Page buildPage(long offset, int limit) {
        Page result = new Page();

        result.setOffset(offset);
        result.setLimit(limit);

        return result;
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
