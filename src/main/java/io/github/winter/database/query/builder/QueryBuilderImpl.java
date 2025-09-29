package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.*;
import io.github.winter.boot.sql.Preconditions;
import io.github.winter.boot.sql.SqlParameter;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.*;
import io.github.winter.database.query.parser.QueryParserImpl;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        List<Map<String, Value>> list = querySelect.selectQueryAll();
        if (list == null) {
            return result;
        }

        for (Map<String, Value> record : list) {
            if (record == null) {
                continue;
            }

            Value value = record.get("id");
            if (value == null) {
                continue;
            }

            Integer id = value.getInteger();
            if (id == null) {
                continue;
            }

            if (id > 0) {
                result.add(id);
            }
        }

        return result;
    }

    @Override
    public Query build(int id) {
        Map<String, Value> record = querySelect.selectQuery(id);
        if (record == null) {
            return null;
        }

        int queryId = QueryGetter.getId(record);
        String fromTable = QueryGetter.getFromTable(record);
        Preconditions.requireNonEmpty(fromTable, "fromTable must not be empty, queryId: " + queryId);

        String queryName = QueryGetter.getName(record);
        boolean distinct = QueryGetter.isDistinct(record);
        boolean asterisk = QueryGetter.isAsterisk(record);
        boolean parameterName = QueryGetter.isParameterName(record);
        Page page = QueryGetter.getPage(record);

        List<Join> joins = selectJoin(queryId);
        List<Column> columns = selectColumn(queryId, fromTable, asterisk, joins);
        List<BaseFilter> filters = selectFilter(BooleanCast.FALSE, queryId, 0, parameterName, fromTable);
        Group group = selectGroup(queryId, parameterName, fromTable);
        List<Order> orders = selectOrder(queryId, fromTable);

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

        return result;
    }

    /**
     * 字段
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @param asterisk  全字段？
     * @param joins     [ the {@link Join} instance ]
     * @return [ the {@link Column} instance ]
     */
    protected List<Column> selectColumn(int queryId, @NotEmpty String fromTable, boolean asterisk, List<Join> joins) {
        List<Column> columns = selectColumn(queryId, fromTable);
        if (!asterisk) {
            return columns;
        }

        List<String> tableNames = TableNameBuilder.build(fromTable, joins);
        List<Column> asteriskColumns = columnBuilder.buildAsterisk(tableNames);
        if (asteriskColumns == null) {
            return columns;
        }

        if (columns == null) {
            return asteriskColumns;
        }

        columns.addAll(asteriskColumns);
        return columns;
    }

    @Override
    public List<Column> selectColumn(int queryId, String fromTable) {
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

            Column column = buildColumn(queryId, funcType, tableName, columnName, asName);
            result.add(column);
        }

        return result;
    }

    @Override
    public List<Join> selectJoin(int queryId) {
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
            List<Join.On> filters = selectJoinOn(queryId, joinId);
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
    public List<Join.On> selectJoinOn(int queryId, int joinId) {
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

            String leftName = joinName(queryId, leftTable, leftColumn);
            String rightName = joinName(queryId, rightTable, rightColumn);

            Join.On filter = new Join.On();

            filter.setLeftName(leftName);
            filter.setRightName(rightName);

            result.add(filter);
        }

        return result;
    }

    @Override
    public Group selectGroup(int queryId, boolean isParameterName, String fromTable) {
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
            String groupName = joinName(queryId, tableName, columnName);
            groupNames.add(groupName);
        }

        List<BaseFilter> filters = selectFilter(BooleanCast.TRUE, queryId, 0, isParameterName, fromTable);

        Group result = new Group();

        result.setNames(groupNames);
        result.setFilters(filters);

        return result;
    }

    @Override
    public List<Order> selectOrder(int queryId, String fromTable) {
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
            String orderName = joinFunc(queryId, funcType, tableName, columnName);

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
    public List<BaseFilter> selectFilter(int isHaving, int queryId, int parentId, boolean isParameterName, String fromTable) {
        List<BaseFilter> result = new ArrayList<>();

        List<QueryFilter> list = querySelect.selectFilter(isHaving, queryId, parentId, fromTable);
        if (list == null) {
            return result;
        }

        for (QueryFilter record : list) {
            if (record == null) {
                continue;
            }

            int logicalOperator = record.getLogicalOperator();
            Boolean isOr = LogicalType.isOr(logicalOperator);

            int filterId = record.getId();
            List<BaseFilter> subFilterList = selectFilter(isHaving, queryId, filterId, isParameterName, fromTable);
            Filters subFilters = null;
            if (!subFilterList.isEmpty()) {
                subFilters = new Filters();
                subFilters.setOr(isOr);
                subFilters.setFilters(subFilterList);
            }

            String columnName = record.getColumnName();
            if (columnName.isEmpty()) {
                if (subFilters != null) {
                    result.add(subFilters);
                }
                continue;
            }

            int funcType = record.getFuncType();
            String tableName = record.getTableName();

            String filterName = joinFunc(queryId, funcType, tableName, columnName);
            Preconditions.requireNonEmpty(filterName, "filterName must not be empty, queryId: " + queryId + ", filterId: " + filterId);

            Class<?> valueType = columnBuilder.parseType(funcType, tableName, columnName);
            Preconditions.requireNonNull(valueType, "valueType must not be null, queryId: " + queryId + ", filterId: " + filterId);

            int filterType = record.getFilterType();
            switch (filterType) {
                case FilterType.EXPRESSION: {
                    QueryFilterExpression expressionRecord = querySelect.selectFilterExpression(queryId, filterId);
                    Preconditions.requireNonNull(expressionRecord, "expressionRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    int expressionCode = expressionRecord.getExpressionCode();
                    String parameterName = ParameterBuilder.getParameterName(expressionRecord, isParameterName, filterName);
                    Parameter parameter = ParameterBuilder.getParameter(parameterName, expressionRecord, valueType);

                    ExpressionFilter expressionFilter = new ExpressionFilter();

                    expressionFilter.setOr(isOr);
                    expressionFilter.setName(filterName);
                    expressionFilter.setCode(expressionCode);
                    expressionFilter.setParameter(parameter);

                    result.add(expressionFilter);
                    break;
                }
                case FilterType.IN: {
                    QueryFilterIn inRecord = querySelect.selectFilterIn(queryId, filterId);
                    Preconditions.requireNonNull(inRecord, "inRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    boolean not = inRecord.isNot();

                    InFilter inFilter = new InFilter();

                    inFilter.setOr(isOr);
                    inFilter.setName(filterName);
                    inFilter.setNot(not);

                    int subQueryId = inRecord.getSubQueryId();
                    if (subQueryId > 0) {
                        Query query = build(subQueryId);
                        Preconditions.requireNonNull(query, "query must not be null, queryId: " + queryId + ", filterId: " + filterId + ", subQueryId: " + subQueryId);

                        SqlParameter sqlParameter = queryParser.parse(query);
                        Preconditions.requireNonNull(sqlParameter, "sqlParameter must not be null, queryId: " + queryId + ", filterId: " + filterId + ", subQueryId: " + subQueryId);

                        String sql = sqlParameter.getOriginalSql();
                        List<Parameter> parameters = sqlParameter.getOriginalParameters();
                        inFilter.setSql(sql);
                        inFilter.setParameters(parameters);
                    } else {
                        String parameterName = ParameterBuilder.getParameterName(inRecord, isParameterName, filterName);
                        List<QueryFilterInValue> inValueList = querySelect.selectFilterInValue(queryId, filterId);

                        List<Parameter> parameters = ParameterBuilder.getParameters(parameterName, inValueList, valueType);
                        Preconditions.requireNonEmpty(parameters, "parameters must not be empty, queryId: " + queryId + ", filterId: " + filterId);

                        inFilter.setParameters(parameters);
                    }

                    result.add(inFilter);
                    break;
                }
                case FilterType.NULL: {
                    QueryFilterNull nullRecord = querySelect.selectFilterNull(queryId, filterId);
                    Preconditions.requireNonNull(nullRecord, "nullRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    boolean not = nullRecord.isNot();

                    NullFilter nullFilter = new NullFilter();

                    nullFilter.setOr(isOr);
                    nullFilter.setName(filterName);
                    nullFilter.setNot(not);

                    result.add(nullFilter);
                    break;
                }
                case FilterType.RANGE: {
                    QueryFilterRange rangeRecord = querySelect.selectFilterRange(queryId, filterId);
                    Preconditions.requireNonNull(rangeRecord, "rangeRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    boolean includeLower = rangeRecord.isIncludeLower();
                    boolean includeUpper = rangeRecord.isIncludeUpper();

                    String fromParameterName = ParameterBuilder.getFromParameterName(rangeRecord, isParameterName, filterName);
                    Parameter fromParameter = ParameterBuilder.getFromParameter(fromParameterName, rangeRecord, valueType);

                    String toParameterName = ParameterBuilder.getToParameterName(rangeRecord, isParameterName, filterName);
                    Parameter toParameter = ParameterBuilder.getToParameter(toParameterName, rangeRecord, valueType);

                    RangeFilter rangeFilter = new RangeFilter();

                    rangeFilter.setOr(isOr);
                    rangeFilter.setName(filterName);
                    rangeFilter.setIncludeLower(includeLower);
                    rangeFilter.setIncludeUpper(includeUpper);
                    rangeFilter.setFrom(fromParameter);
                    rangeFilter.setTo(toParameter);

                    result.add(rangeFilter);
                    break;
                }
                case FilterType.WILDCARD: {
                    QueryFilterWildcard wildcardRecord = querySelect.selectFilterWildcard(queryId, filterId);
                    Preconditions.requireNonNull(wildcardRecord, "wildcardRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    boolean not = wildcardRecord.isNot();
                    int wildcardCode = wildcardRecord.getWildcardCode();
                    String parameterName = ParameterBuilder.getParameterName(wildcardRecord, isParameterName, filterName);
                    Parameter parameter = ParameterBuilder.getParameter(parameterName, wildcardRecord, valueType);

                    WildcardFilter wildcardFilter = new WildcardFilter();

                    wildcardFilter.setOr(isOr);
                    wildcardFilter.setName(filterName);
                    wildcardFilter.setNot(not);
                    wildcardFilter.setCode(wildcardCode);
                    wildcardFilter.setParameter(parameter);

                    result.add(wildcardFilter);
                    break;
                }
                default:
                    if (subFilters != null) {
                        result.add(subFilters);
                    }
                    break;
            }
        }

        return result;
    }

    /**
     * 创建字段
     *
     * @param queryId    查询主键
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @param asName     别名
     * @return the {@link Column} instance
     */
    @NotNull
    protected Column buildColumn(int queryId, int funcType, @NotNull String tableName, @NotNull String columnName, String asName) {
        Preconditions.requireNonEmpty(tableName, "tableName must not be empty, queryId: " + queryId);
        Preconditions.requireNonEmpty(columnName, "columnName must not be empty, queryId: " + queryId);
        return columnBuilder.build(funcType, tableName, columnName, asName);
    }

    /**
     * 连接函数
     *
     * @param queryId    查询主键
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @return 函数 + 表名 + 字段名
     */
    @NotNull
    protected String joinFunc(int queryId, int funcType, String tableName, String columnName) {
        Preconditions.requireNonEmpty(tableName, "tableName must not be empty, queryId: " + queryId);
        Preconditions.requireNonEmpty(columnName, "columnName must not be empty, queryId: " + queryId);
        return columnBuilder.joinFunc(funcType, tableName, columnName);
    }

    /**
     * 连接表名
     *
     * @param queryId    查询主键
     * @param tableName  表名
     * @param columnName 字段名
     * @return 表名 + 字段名
     */
    @NotNull
    protected String joinName(int queryId, String tableName, String columnName) {
        Preconditions.requireNonEmpty(tableName, "tableName must not be empty, queryId: " + queryId);
        Preconditions.requireNonEmpty(columnName, "columnName must not be empty, queryId: " + queryId);
        return columnBuilder.joinName(tableName, columnName);
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
