package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.*;
import io.github.winter.boot.sql.Preconditions;
import io.github.winter.boot.tuple.Pair;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.*;
import io.github.winter.database.query.dto.*;
import io.github.winter.database.query.parser.FuncParserImpl;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author changebooks@qq.com
 */
public class QueryBuilderImpl implements QueryBuilder {
    /**
     * the {@link QuerySelect} instance
     */
    private final QuerySelect querySelect;

    /**
     * the {@link FuncParser} instance
     */
    private final FuncParser funcParser;

    public QueryBuilderImpl(JdbcTemplate jdbcTemplate) {
        this.querySelect = new QuerySelect(jdbcTemplate);
        this.funcParser = new FuncParserImpl();
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

        boolean distinct = record.getDistinct();
        boolean asterisk = record.getAsterisk();
        Long pageOffset = record.getPageOffset();
        Integer pageLimit = record.getPageLimit();

        String queryName = record.getQueryName();
        String queryDescription = record.getQueryDescription();
        String queryRemark = record.getQueryRemark();

        Pair<List<String>, Map<String, Class<?>>> columnPair = selectColumn(queryId, fromTable, asterisk);
        List<String> columns = columnPair.first;
        Map<String, Class<?>> valueTypes = columnPair.second;

        List<Join> joins = selectJoin(queryId);
        List<BaseFilter> filters = selectFilter(BooleanCast.FALSE, queryId, 0, fromTable);
        Group group = selectGroup(queryId, fromTable);
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
        result.setValueTypes(valueTypes);

        return result;
    }

    @Override
    public Pair<List<String>, Map<String, Class<?>>> selectColumn(int queryId, String fromTable) {
        List<QueryColumnDto> list = querySelect.selectColumn(queryId);
        if (list == null) {
            return Pair.of(null, null);
        }

        List<String> fieldNames = new ArrayList<>();
        Map<String, Class<?>> valueTypes = new HashMap<>();

        for (QueryColumnDto record : list) {
            if (record == null) {
                continue;
            }

            int funcType = record.getFuncType();
            if (funcType == FuncType.COUNT) {
                fieldNames.add(FuncParser.COUNT);
                valueTypes.put(FuncParser.COUNT, Long.class);
                continue;
            }

            String tableName = TableName.defaultName(record.getTableName(), fromTable);
            String columnName = record.getColumnName();

            String fieldName = joinName(tableName, columnName);
            Class<?> valueType = getClass(tableName, columnName);

            fieldNames.add(fieldName);
            valueTypes.put(fieldName, valueType);
        }

        return Pair.of(fieldNames, valueTypes);
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

            String leftName = joinName(leftTable, leftColumn);
            String rightName = joinName(rightTable, rightColumn);

            Join.On filter = new Join.On();

            filter.setLeftName(leftName);
            filter.setRightName(rightName);

            result.add(filter);
        }

        return result;
    }

    @Override
    public Group selectGroup(int queryId, String fromTable) {
        List<QueryGroupDto> list = querySelect.selectGroup(queryId);
        if (list == null) {
            return null;
        }

        List<String> groupNames = new ArrayList<>();

        for (QueryGroupDto record : list) {
            if (record == null) {
                continue;
            }

            String tableName = TableName.defaultName(record.getTableName(), fromTable);
            String columnName = record.getColumnName();
            String groupName = joinName(tableName, columnName);

            groupNames.add(groupName);
        }

        List<BaseFilter> filters = selectFilter(BooleanCast.TRUE, queryId, 0, fromTable);

        Group result = new Group();

        result.setNames(groupNames);
        result.setFilters(filters);

        return result;
    }

    @Override
    public List<Order> selectOrder(int queryId, String fromTable) {
        List<QueryOrderDto> list = querySelect.selectOrder(queryId);
        if (list == null) {
            return null;
        }

        List<Order> result = new ArrayList<>();

        for (QueryOrderDto record : list) {
            if (record == null) {
                continue;
            }

            int funcType = record.getFuncType();
            String tableName = TableName.defaultName(record.getTableName(), fromTable);
            String columnName = record.getColumnName();
            String orderName = joinName(funcType, tableName, columnName);

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
    public List<BaseFilter> selectFilter(int isHaving, int queryId, int parentId, String fromTable) {
        List<QueryFilterDto> list = querySelect.selectFilter(isHaving, queryId, parentId);
        if (list == null) {
            return null;
        }

        List<BaseFilter> result = new ArrayList<>();

        for (QueryFilterDto record : list) {
            if (record == null) {
                continue;
            }

            int funcType = record.getFuncType();
            String tableName = TableName.defaultName(record.getTableName(), fromTable);
            String columnName = record.getColumnName();
            String filterName = joinName(funcType, tableName, columnName);
            Class<?> valueType = getClass(tableName, columnName);

            int filterId = record.getId();
            int filterType = record.getFilterType();
            int logicalOperator = record.getLogicalOperator();
            Boolean isOr = LogicalType.isOr(logicalOperator);

            switch (filterType) {
                case FilterType.EXPRESSION: {
                    QueryFilterExpressionDto expressionRecord = querySelect.selectFilterExpression(queryId, filterId);
                    Preconditions.requireNonNull(expressionRecord, "expressionRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    int expressionCode = expressionRecord.getExpressionCode();

                    String valueString = expressionRecord.getValueString();
                    Integer valueInteger = expressionRecord.getValueInteger();
                    Long valueLong = expressionRecord.getValueLong();
                    BigDecimal valueBigDecimal = expressionRecord.getValueBigDecimal();
                    Date valueDate = expressionRecord.getValueDate();
                    Value value = ValueBuilder.build(valueType, valueString, valueInteger, valueLong, valueBigDecimal, valueDate);

                    Parameter parameter = new Parameter();
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

                    List<QueryFilterInValueDto> inValueList = querySelect.selectFilterInValue(queryId, filterId);
                    Preconditions.requireNonEmpty(inValueList, "inValueList must not be empty, queryId: " + queryId + ", filterId: " + filterId);

                    Boolean isNot = inRecord.getNot();
                    List<Parameter> parameters = new ArrayList<>();

                    for (QueryFilterInValueDto inValueRecord : inValueList) {
                        if (inValueRecord == null) {
                            continue;
                        }

                        String valueString = inValueRecord.getValueString();
                        Integer valueInteger = inValueRecord.getValueInteger();
                        Long valueLong = inValueRecord.getValueLong();
                        BigDecimal valueBigDecimal = inValueRecord.getValueBigDecimal();
                        Date valueDate = inValueRecord.getValueDate();
                        Value value = ValueBuilder.build(valueType, valueString, valueInteger, valueLong, valueBigDecimal, valueDate);

                        Parameter parameter = new Parameter();
                        parameter.setValue(value);

                        parameters.add(parameter);
                    }

                    InFilter inFilter = new InFilter();

                    inFilter.setOr(isOr);
                    inFilter.setName(filterName);
                    inFilter.setNot(isNot);
                    inFilter.setParameters(parameters);

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
                    String fromValueString = rangeRecord.getFromValueString();
                    Integer fromValueInteger = rangeRecord.getFromValueInteger();
                    Long fromValueLong = rangeRecord.getFromValueLong();
                    BigDecimal fromValueBigDecimal = rangeRecord.getFromValueBigDecimal();
                    Date fromValueDate = rangeRecord.getFromValueDate();
                    String toValueString = rangeRecord.getToValueString();
                    Integer toValueInteger = rangeRecord.getToValueInteger();
                    Long toValueLong = rangeRecord.getToValueLong();
                    BigDecimal toValueBigDecimal = rangeRecord.getToValueBigDecimal();
                    Date toValueDate = rangeRecord.getToValueDate();

                    Value fromValue = ValueBuilder.build(valueType, fromValueString, fromValueInteger, fromValueLong, fromValueBigDecimal, fromValueDate);
                    Value toValue = ValueBuilder.build(valueType, toValueString, toValueInteger, toValueLong, toValueBigDecimal, toValueDate);

                    Parameter from = new Parameter();
                    from.setValue(fromValue);

                    Parameter to = new Parameter();
                    to.setValue(toValue);

                    RangeFilter rangeFilter = new RangeFilter();

                    rangeFilter.setOr(isOr);
                    rangeFilter.setName(filterName);
                    rangeFilter.setFrom(from);
                    rangeFilter.setTo(to);
                    rangeFilter.setIncludeLower(isIncludeLower);
                    rangeFilter.setIncludeUpper(isIncludeUpper);

                    result.add(rangeFilter);
                    break;
                }
                case FilterType.WILDCARD: {
                    QueryFilterWildcardDto wildcardRecord = querySelect.selectFilterWildcard(queryId, filterId);
                    Preconditions.requireNonNull(wildcardRecord, "wildcardRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    Boolean isNot = wildcardRecord.getNot();
                    int wildcardCode = wildcardRecord.getWildcardCode();

                    String valueString = wildcardRecord.getValueString();
                    Integer valueInteger = wildcardRecord.getValueInteger();
                    Long valueLong = wildcardRecord.getValueLong();
                    BigDecimal valueBigDecimal = wildcardRecord.getValueBigDecimal();
                    Date valueDate = wildcardRecord.getValueDate();
                    Value value = ValueBuilder.build(valueType, valueString, valueInteger, valueLong, valueBigDecimal, valueDate);

                    Parameter parameter = new Parameter();
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
                    break;
            }

            List<BaseFilter> filterList = selectFilter(isHaving, queryId, filterId, fromTable);
            if (filterList == null || filterList.isEmpty()) {
                continue;
            }

            Filters filters = new Filters();
            filters.setFilters(filterList);

            result.add(filters);
        }

        return result;
    }

    @Override
    public String parseFunc(int funcType, String tableName, String columnName) {
        return funcParser.parse(funcType, tableName, columnName);
    }

    @Override
    public String parseFunc(int funcType, String name) {
        return funcParser.parse(funcType, name);
    }

    public QuerySelect getQuerySelect() {
        return querySelect;
    }

    public FuncParser getFuncParser() {
        return funcParser;
    }

}
