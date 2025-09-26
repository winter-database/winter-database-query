package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.*;
import io.github.winter.boot.sql.Preconditions;
import io.github.winter.boot.sql.SqlParameter;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.*;
import io.github.winter.database.query.dto.*;
import io.github.winter.database.query.parser.QueryParserImpl;
import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;
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

        List<QueryDto> list = querySelect.selectQueryAll();
        if (list == null) {
            return result;
        }

        for (QueryDto record : list) {
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
        QueryDto record = querySelect.selectQuery(id);
        if (record == null) {
            return null;
        }

        int queryId = record.getId();
        String fromTable = record.getFromTable();
        Preconditions.requireNonEmpty(fromTable, "fromTable must not be empty, queryId: " + queryId);

        boolean subQuery = record.getSubQuery();
        boolean distinct = record.getDistinct();
        boolean asterisk = record.getAsterisk();
        Long pageOffset = record.getPageOffset();
        int pageLimit = record.getPageLimit();

        String queryName = record.getQueryName();
        String queryDescription = record.getQueryDescription();
        String queryRemark = record.getQueryRemark();

        List<Join> joins = selectJoin(queryId);
        List<Column> columns = selectColumn(queryId, fromTable, asterisk, joins);
        List<BaseFilter> filters = selectFilter(BooleanCast.FALSE, queryId, 0, subQuery, fromTable);
        Group group = selectGroup(queryId, subQuery, fromTable);
        List<Order> orders = selectOrder(queryId, fromTable);

        Page page = new Page();
        page.setOffset(pageOffset);
        page.setLimit(pageLimit);

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

    @Override
    public String getSubQuery(int id) {
        Query query = build(id);
        if (query == null) {
            return null;
        }

        SqlParameter sqlParameter = queryParser.parse(query);
        if (sqlParameter != null) {
            return sqlParameter.getSql();
        } else {
            return null;
        }
    }

    @Override
    public List<Column> selectColumn(int queryId, String fromTable) {
        List<QueryColumnDto> list = querySelect.selectColumn(queryId, fromTable);
        if (list == null) {
            return null;
        }

        List<Column> result = new ArrayList<>();

        for (QueryColumnDto record : list) {
            if (record == null) {
                continue;
            }

            String tableName = record.getTableName();
            Preconditions.requireNonEmpty(tableName, "tableName must not be empty, queryId: " + queryId);

            String columnName = record.getColumnName();
            Preconditions.requireNonEmpty(columnName, "columnName must not be empty, queryId: " + queryId);

            int funcType = record.getFuncType();
            String asName = record.getAsName();

            Column column = columnBuilder.build(funcType, tableName, columnName, asName);
            result.add(column);
        }

        return result;
    }

    @Override
    public List<Column> selectAsterisk(String fromTable) {
        TableSchema tableSchema = TableSchemaRegistry.get(fromTable);
        Preconditions.requireNonNull(tableSchema, "tableSchema must not be null, fromTable: " + fromTable);

        String tableName = tableSchema.getTableName();
        Preconditions.requireNonEmpty(tableName, "tableName must not be empty, fromTable: " + fromTable);

        List<String> columnNames = tableSchema.getColumnNames();
        Preconditions.requireNonEmpty(columnNames, "columnNames must not be empty, fromTable: " + fromTable);

        List<Column> result = new ArrayList<>();

        for (String columnName : columnNames) {
            Preconditions.requireNonNull(columnName, "columnName must not be null, fromTable: " + fromTable);
            Preconditions.requireNonEmpty(columnName, "columnName must not be empty, fromTable: " + fromTable);

            Column column = columnBuilder.build(FuncType.NULL, tableName, columnName, null);
            result.add(column);
        }

        return result;
    }

    @Override
    public List<Join> selectJoin(int queryId) {
        List<QueryJoinDto> list = querySelect.selectJoin(queryId);
        if (list == null) {
            return null;
        }

        List<Join> result = new ArrayList<>();

        for (QueryJoinDto record : list) {
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
        List<QueryJoinOnDto> list = querySelect.selectJoinOn(queryId, joinId);
        if (list == null) {
            return null;
        }

        List<Join.On> result = new ArrayList<>();

        for (QueryJoinOnDto record : list) {
            if (record == null) {
                continue;
            }

            String leftTable = record.getLeftTable();
            String leftColumn = record.getLeftColumn();
            String rightTable = record.getRightTable();
            String rightColumn = record.getRightColumn();

            String leftName = columnBuilder.joinName(leftTable, leftColumn);
            Preconditions.requireNonEmpty(leftName, "leftName must not be empty, queryId: " + queryId + ", joinId: " + joinId);

            String rightName = columnBuilder.joinName(rightTable, rightColumn);
            Preconditions.requireNonEmpty(rightName, "rightName must not be empty, queryId: " + queryId + ", joinId: " + joinId);

            Join.On filter = new Join.On();

            filter.setLeftName(leftName);
            filter.setRightName(rightName);

            result.add(filter);
        }

        return result;
    }

    @Override
    public Group selectGroup(int queryId, boolean subQuery, String fromTable) {
        List<QueryGroupDto> list = querySelect.selectGroup(queryId, fromTable);
        if (list == null) {
            return null;
        }

        List<String> groupNames = new ArrayList<>();

        for (QueryGroupDto record : list) {
            if (record == null) {
                continue;
            }

            String tableName = record.getTableName();
            Preconditions.requireNonEmpty(tableName, "tableName must not be empty, queryId: " + queryId);

            String columnName = record.getColumnName();
            Preconditions.requireNonEmpty(columnName, "columnName must not be empty, queryId: " + queryId);

            String groupName = columnBuilder.joinName(tableName, columnName);
            groupNames.add(groupName);
        }

        List<BaseFilter> filters = selectFilter(BooleanCast.TRUE, queryId, 0, subQuery, fromTable);

        Group result = new Group();

        result.setNames(groupNames);
        result.setFilters(filters);

        return result;
    }

    @Override
    public List<Order> selectOrder(int queryId, String fromTable) {
        List<QueryOrderDto> list = querySelect.selectOrder(queryId, fromTable);
        if (list == null) {
            return null;
        }

        List<Order> result = new ArrayList<>();

        for (QueryOrderDto record : list) {
            if (record == null) {
                continue;
            }

            String tableName = record.getTableName();
            Preconditions.requireNonEmpty(tableName, "tableName must not be empty, queryId: " + queryId);

            String columnName = record.getColumnName();
            Preconditions.requireNonEmpty(columnName, "columnName must not be empty, queryId: " + queryId);

            int funcType = record.getFuncType();
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
    public List<BaseFilter> selectFilter(int isHaving, int queryId, int parentId, boolean subQuery, String fromTable) {
        List<QueryFilterDto> list = querySelect.selectFilter(isHaving, queryId, parentId, fromTable);
        if (list == null) {
            return null;
        }

        List<BaseFilter> result = new ArrayList<>();

        for (QueryFilterDto record : list) {
            if (record == null) {
                continue;
            }

            int logicalOperator = record.getLogicalOperator();
            Boolean isOr = LogicalType.isOr(logicalOperator);

            int filterId = record.getId();
            List<BaseFilter> subFilterList = selectFilter(isHaving, queryId, filterId, subQuery, fromTable);
            Filters subFilters = null;
            if (subFilterList != null && !subFilterList.isEmpty()) {
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

            String tableName = record.getTableName();
            Preconditions.requireNonEmpty(tableName, "tableName must not be empty, queryId: " + queryId + ", filterId: " + filterId);

            int funcType = record.getFuncType();
            String filterName = columnBuilder.joinFunc(funcType, tableName, columnName);

            Class<?> valueType = columnBuilder.parseType(funcType, tableName, columnName);
            Preconditions.requireNonNull(valueType, "valueType must not be null, queryId: " + queryId + ", filterId: " + filterId);

            int filterType = record.getFilterType();
            switch (filterType) {
                case FilterType.EXPRESSION: {
                    QueryFilterExpressionDto expressionRecord = querySelect.selectFilterExpression(queryId, filterId);
                    Preconditions.requireNonNull(expressionRecord, "expressionRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    int expressionCode = expressionRecord.getExpressionCode();
                    String parameterName = getParameterName(subQuery, expressionRecord.getParameterName(), filterName);
                    Value value = Value.newInstance
                            (
                                    valueType,
                                    expressionRecord.getValueString(),
                                    expressionRecord.getValueInteger(),
                                    expressionRecord.getValueLong(),
                                    expressionRecord.getValueBigDecimal(),
                                    expressionRecord.getValueDate()
                            );

                    Parameter parameter = new Parameter();
                    parameter.setName(parameterName);
                    parameter.setValue(value);

                    ExpressionFilter expressionFilter = new ExpressionFilter();

                    expressionFilter.setOr(isOr);
                    expressionFilter.setName(filterName);
                    expressionFilter.setCode(expressionCode);
                    expressionFilter.setParameter(parameter);

                    result.add(expressionFilter);
                    break;
                }
                case FilterType.IN: {
                    QueryFilterInDto inRecord = querySelect.selectFilterIn(queryId, filterId);
                    Preconditions.requireNonNull(inRecord, "inRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    Boolean isNot = inRecord.getNot();
                    String parameterName = getParameterName(subQuery, inRecord.getParameterName(), filterName);

                    List<Parameter> parameters = new ArrayList<>();

                    List<QueryFilterInValueDto> inValueList = querySelect.selectFilterInValue(queryId, filterId);
                    if (inValueList != null) {
                        for (QueryFilterInValueDto inValueRecord : inValueList) {
                            if (inValueRecord == null) {
                                continue;
                            }

                            Value value = Value.newInstance
                                    (
                                            valueType,
                                            inValueRecord.getValueString(),
                                            inValueRecord.getValueInteger(),
                                            inValueRecord.getValueLong(),
                                            inValueRecord.getValueBigDecimal(),
                                            inValueRecord.getValueDate()
                                    );

                            Parameter parameter = new Parameter();
                            parameter.setName(parameterName);
                            parameter.setValue(value);

                            parameters.add(parameter);
                        }
                    }

                    int subQueryId = inRecord.getSubQueryId();
                    String subSql = (subQueryId > 0) ? getSubQuery(subQueryId) : "";

                    InFilter inFilter = new InFilter();

                    inFilter.setOr(isOr);
                    inFilter.setName(filterName);
                    inFilter.setNot(isNot);
                    inFilter.setParameters(parameters);
                    inFilter.setSql(subSql);

                    result.add(inFilter);
                    break;
                }
                case FilterType.NULL: {
                    QueryFilterNullDto nullRecord = querySelect.selectFilterNull(queryId, filterId);
                    Preconditions.requireNonNull(nullRecord, "nullRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    Boolean isNot = nullRecord.getNot();

                    NullFilter nullFilter = new NullFilter();

                    nullFilter.setOr(isOr);
                    nullFilter.setName(filterName);
                    nullFilter.setNot(isNot);

                    result.add(nullFilter);
                    break;
                }
                case FilterType.RANGE: {
                    QueryFilterRangeDto rangeRecord = querySelect.selectFilterRange(queryId, filterId);
                    Preconditions.requireNonNull(rangeRecord, "rangeRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    Boolean isIncludeLower = rangeRecord.getIncludeLower();
                    Boolean isIncludeUpper = rangeRecord.getIncludeUpper();

                    RangeFilter rangeFilter = new RangeFilter();

                    rangeFilter.setOr(isOr);
                    rangeFilter.setName(filterName);
                    rangeFilter.setIncludeLower(isIncludeLower);
                    rangeFilter.setIncludeUpper(isIncludeUpper);

                    String fromParameterName = getParameterName(subQuery, rangeRecord.getFromParameterName(), "");
                    Value fromValue = Value.newInstance
                            (
                                    valueType,
                                    rangeRecord.getFromValueString(),
                                    rangeRecord.getFromValueInteger(),
                                    rangeRecord.getFromValueLong(),
                                    rangeRecord.getFromValueBigDecimal(),
                                    rangeRecord.getFromValueDate()
                            );
                    if (fromValue.toObject() != null) {
                        if (subQuery || !fromParameterName.isEmpty()) {
                            Parameter from = new Parameter();
                            from.setName(fromParameterName);
                            from.setValue(fromValue);
                            rangeFilter.setFrom(from);
                        }
                    }

                    String toParameterName = getParameterName(subQuery, rangeRecord.getToParameterName(), "");
                    Value toValue = Value.newInstance
                            (
                                    valueType,
                                    rangeRecord.getToValueString(),
                                    rangeRecord.getToValueInteger(),
                                    rangeRecord.getToValueLong(),
                                    rangeRecord.getToValueBigDecimal(),
                                    rangeRecord.getToValueDate()
                            );
                    if (toValue.toObject() != null) {
                        if (subQuery || !toParameterName.isEmpty()) {
                            Parameter to = new Parameter();
                            to.setName(toParameterName);
                            to.setValue(toValue);
                            rangeFilter.setTo(to);
                        }
                    }

                    result.add(rangeFilter);
                    break;
                }
                case FilterType.WILDCARD: {
                    QueryFilterWildcardDto wildcardRecord = querySelect.selectFilterWildcard(queryId, filterId);
                    Preconditions.requireNonNull(wildcardRecord, "wildcardRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    Boolean isNot = wildcardRecord.getNot();
                    int wildcardCode = wildcardRecord.getWildcardCode();
                    String parameterName = getParameterName(subQuery, wildcardRecord.getParameterName(), filterName);
                    Value value = Value.newInstance
                            (
                                    valueType,
                                    wildcardRecord.getValueString(),
                                    wildcardRecord.getValueInteger(),
                                    wildcardRecord.getValueLong(),
                                    wildcardRecord.getValueBigDecimal(),
                                    wildcardRecord.getValueDate()
                            );

                    Parameter parameter = new Parameter();
                    parameter.setName(parameterName);
                    parameter.setValue(value);

                    WildcardFilter wildcardFilter = new WildcardFilter();

                    wildcardFilter.setOr(isOr);
                    wildcardFilter.setName(filterName);
                    wildcardFilter.setNot(isNot);
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
