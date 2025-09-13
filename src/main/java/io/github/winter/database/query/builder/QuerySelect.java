package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.*;
import io.github.winter.boot.sql.Preconditions;
import io.github.winter.boot.tuple.Pair;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.*;
import io.github.winter.database.query.dto.*;
import io.github.winter.database.query.parser.FuncParserImpl;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.*;

/**
 * Query Select
 *
 * @author changebooks@qq.com
 */
public class QuerySelect {
    /**
     * the {@link QueryDtoSelect} instance
     */
    private final QueryDtoSelect queryDtoSelect;

    /**
     * the {@link FuncParser} instance
     */
    private final FuncParser funcParser;

    public QuerySelect(JdbcTemplate jdbcTemplate) {
        this.queryDtoSelect = new QueryDtoSelect(jdbcTemplate);
        this.funcParser = new FuncParserImpl();
    }

    /**
     * 查询
     *
     * @param id 主键
     * @return the {@link Query} instance
     */
    public Query selectQuery(int id) {
        QueryDto record = queryDtoSelect.selectQuery(id);
        if (record == null) {
            return null;
        }

        int queryId = record.getId();
        String queryName = record.getQueryName();
        boolean isDistinct = record.getDistinct();
        boolean isAsterisk = record.getAsterisk();
        String fromTable = record.getFromTable();
        Long pageOffset = record.getPageOffset();
        Integer pageLimit = record.getPageLimit();
        String queryDescription = record.getQueryDescription();
        String queryRemark = record.getQueryRemark();

        Page page = new Page();
        page.setOffset(pageOffset);
        page.setLimit(pageLimit);

        List<String> columns = null;
        Map<String, Class<?>> valueTypes = null;
        if (isAsterisk) {
            Pair<List<String>, Map<String, Class<?>>> asteriskPair = AsteriskBuilder.build(fromTable);
            columns = asteriskPair.first;
            valueTypes = asteriskPair.second;
        }

        Query result = new Query();

        result.setId(queryId);
        result.setName(queryName);
        result.setDistinct(isDistinct);
        result.setColumns(columns);
        result.setTableName(fromTable);
        result.setPage(page);
        result.setDescription(queryDescription);
        result.setRemark(queryRemark);
        result.setValueTypes(valueTypes);

        return result;
    }

    /**
     * 字段
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ [ 字段名 ] : [ 字段名 : 字段类型 ] ]
     */
    @NotNull
    public Pair<List<String>, Map<String, Class<?>>> selectColumn(int queryId, @NotEmpty String fromTable) {
        List<QueryColumnDto> list = queryDtoSelect.selectColumn(queryId);
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

            String tableName = NameJoiner.defaultName(record.getTableName(), fromTable);
            String columnName = record.getColumnName();
            String fieldName = funcParser.parse(funcType, tableName, columnName);
            Preconditions.requireNonEmpty(fieldName, "fieldName must not be empty, tableName: " + tableName + ", columnName: " + columnName);

            Class<?> valueType = ValueTypeGetter.get(tableName, columnName);
            Preconditions.requireNonNull(valueType, "valueType must not be null, tableName: " + tableName + ", columnName: " + columnName);

            fieldNames.add(fieldName);
            valueTypes.put(fieldName, valueType);
        }

        return Pair.of(fieldNames, valueTypes);
    }

    /**
     * 连表
     *
     * @param queryId 查询主键
     * @return [ the {@link Join} instance ]
     */
    public List<Join> selectJoin(int queryId) {
        List<QueryJoinDto> list = queryDtoSelect.selectJoin(queryId);
        if (list == null) {
            return null;
        }

        List<Join> result = new ArrayList<>();

        for (QueryJoinDto record : list) {
            if (record == null) {
                continue;
            }

            String joinTable = record.getJoinTable();
            Preconditions.requireNonEmpty(joinTable, "joinTable must not be empty");

            int joinType = record.getJoinType();
            int joinId = record.getId();
            List<Join.On> filters = selectJoinOn(queryId, joinId);
            Preconditions.requireNonNull(filters, "filters must not be null");
            Preconditions.requireNonEmpty(filters, "filters must not be empty");

            Join join = new Join();

            join.setTableName(joinTable);
            join.setType(joinType);
            join.setFilters(filters);

            result.add(join);
        }

        return result;
    }

    /**
     * 连表条件
     *
     * @param queryId 查询主键
     * @param joinId  连表主键
     * @return [ the {@link Join.On} instance ]
     */
    public List<Join.On> selectJoinOn(int queryId, int joinId) {
        List<QueryJoinOnDto> list = queryDtoSelect.selectJoinOn(queryId, joinId);
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

            String leftName = NameJoiner.join(leftTable, leftColumn);
            Preconditions.requireNonEmpty(leftName, "leftName must not be empty, leftTable: " + leftTable + ", leftColumn: " + leftColumn);

            String rightName = NameJoiner.join(rightTable, rightColumn);
            Preconditions.requireNonEmpty(rightName, "rightName must not be empty, rightTable: " + rightTable + ", rightColumn: " + rightColumn);

            Join.On on = new Join.On();

            on.setLeftName(leftName);
            on.setRightName(rightName);

            result.add(on);
        }

        return result;
    }

    /**
     * 分组
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return the {@link Group} instance
     */
    public Group selectGroup(int queryId, @NotEmpty String fromTable) {
        List<QueryGroupDto> list = queryDtoSelect.selectGroup(queryId);
        if (list == null) {
            return null;
        }

        List<String> groupNames = new ArrayList<>();

        for (QueryGroupDto record : list) {
            if (record == null) {
                continue;
            }

            String tableName = NameJoiner.defaultName(record.getTableName(), fromTable);
            String columnName = record.getColumnName();
            String groupName = NameJoiner.join(tableName, columnName);
            Preconditions.requireNonEmpty(groupName, "groupName must not be empty, tableName: " + tableName + ", columnName: " + columnName);

            groupNames.add(groupName);
        }

        List<BaseFilter> filters = selectFilter(BooleanCast.TRUE, queryId, 0, fromTable);

        Group result = new Group();

        result.setNames(groupNames);
        result.setFilters(filters);

        return result;
    }

    /**
     * 排序
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ the {@link Order} instance ]
     */
    public List<Order> selectOrder(int queryId, @NotEmpty String fromTable) {
        List<QueryOrderDto> list = queryDtoSelect.selectOrder(queryId);
        if (list == null) {
            return null;
        }

        List<Order> result = new ArrayList<>();

        for (QueryOrderDto record : list) {
            if (record == null) {
                continue;
            }

            int funcType = record.getFuncType();
            String tableName = NameJoiner.defaultName(record.getTableName(), fromTable);
            String columnName = record.getColumnName();
            String orderName = funcParser.parse(funcType, tableName, columnName);
            Preconditions.requireNonEmpty(orderName, "orderName must not be empty, tableName: " + tableName + ", columnName: " + columnName);

            int orderType = record.getOrderType();
            Boolean desc = OrderType.isDesc(orderType);

            Order order = new Order();

            order.setName(orderName);
            order.setDesc(desc);

            result.add(order);
        }

        return result;
    }

    /**
     * 条件
     *
     * @param isHaving  分组条件？
     * @param queryId   查询主键
     * @param parentId  父条件主键
     * @param fromTable 表名
     * @return [ the {@link BaseFilter} instance ]
     */
    public List<BaseFilter> selectFilter(int isHaving, int queryId, int parentId, @NotEmpty String fromTable) {
        List<QueryFilterDto> list = queryDtoSelect.selectFilter(isHaving, queryId, parentId);
        if (list == null) {
            return null;
        }

        List<BaseFilter> result = new ArrayList<>();

        for (QueryFilterDto record : list) {
            if (record == null) {
                continue;
            }

            int funcType = record.getFuncType();
            String tableName = NameJoiner.defaultName(record.getTableName(), fromTable);
            String columnName = record.getColumnName();
            String filterName = funcParser.parse(funcType, tableName, columnName);
            Preconditions.requireNonEmpty(filterName, "filterName must not be empty, tableName: " + tableName + ", columnName: " + columnName);

            Class<?> valueType = ValueTypeGetter.get(tableName, columnName);
            Preconditions.requireNonNull(valueType, "valueType must not be null, tableName: " + tableName + ", columnName: " + columnName);

            int filterId = record.getId();
            int filterType = record.getFilterType();
            int logicalOperator = record.getLogicalOperator();
            Boolean or = LogicalType.isOr(logicalOperator);

            switch (filterType) {
                case FilterType.EXPRESSION: {
                    QueryFilterExpressionDto expressionRecord = queryDtoSelect.selectFilterExpression(queryId, filterId);
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

                    expressionFilter.setOr(or);
                    expressionFilter.setName(filterName);
                    expressionFilter.setCode(expressionCode);
                    expressionFilter.setParameter(parameter);

                    result.add(expressionFilter);
                    break;
                }
                case FilterType.IN: {
                    QueryFilterInDto inRecord = queryDtoSelect.selectFilterIn(queryId, filterId);
                    Preconditions.requireNonNull(inRecord, "inRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    List<QueryFilterInValueDto> inValueList = queryDtoSelect.selectFilterInValue(queryId, filterId);
                    Preconditions.requireNonEmpty(inValueList, "inValueList must not be empty, queryId: " + queryId + ", filterId: " + filterId);

                    Boolean not = inRecord.getNot();
                    List<Parameter> parameters = new ArrayList<>();

                    for (QueryFilterInValueDto inValue : inValueList) {
                        if (inValue == null) {
                            continue;
                        }

                        String valueString = inValue.getValueString();
                        Integer valueInteger = inValue.getValueInteger();
                        Long valueLong = inValue.getValueLong();
                        BigDecimal valueBigDecimal = inValue.getValueBigDecimal();
                        Date valueDate = inValue.getValueDate();
                        Value value = ValueBuilder.build(valueType, valueString, valueInteger, valueLong, valueBigDecimal, valueDate);

                        Parameter parameter = new Parameter();
                        parameter.setValue(value);

                        parameters.add(parameter);
                    }

                    InFilter inFilter = new InFilter();

                    inFilter.setOr(or);
                    inFilter.setName(filterName);
                    inFilter.setNot(not);
                    inFilter.setParameters(parameters);

                    result.add(inFilter);
                    break;
                }
                case FilterType.NULL: {
                    QueryFilterNullDto nullRecord = queryDtoSelect.selectFilterNull(queryId, filterId);
                    Preconditions.requireNonNull(nullRecord, "nullRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    Boolean not = nullRecord.getNot();

                    NullFilter nullFilter = new NullFilter();

                    nullFilter.setOr(or);
                    nullFilter.setName(filterName);
                    nullFilter.setNot(not);

                    result.add(nullFilter);
                    break;
                }
                case FilterType.RANGE: {
                    QueryFilterRangeDto rangeRecord = queryDtoSelect.selectFilterRange(queryId, filterId);
                    Preconditions.requireNonNull(rangeRecord, "rangeRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    Boolean includeLower = rangeRecord.getIncludeLower();
                    Boolean includeUpper = rangeRecord.getIncludeUpper();
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

                    rangeFilter.setOr(or);
                    rangeFilter.setName(filterName);
                    rangeFilter.setFrom(from);
                    rangeFilter.setTo(to);
                    rangeFilter.setIncludeLower(includeLower);
                    rangeFilter.setIncludeUpper(includeUpper);

                    result.add(rangeFilter);
                    break;
                }
                case FilterType.WILDCARD: {
                    QueryFilterWildcardDto wildcardRecord = queryDtoSelect.selectFilterWildcard(queryId, filterId);
                    Preconditions.requireNonNull(wildcardRecord, "wildcardRecord must not be null, queryId: " + queryId + ", filterId: " + filterId);

                    Boolean not = wildcardRecord.getNot();
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

                    wildcardFilter.setOr(or);
                    wildcardFilter.setName(filterName);
                    wildcardFilter.setNot(not);
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

    public QueryDtoSelect getQueryDtoSelect() {
        return queryDtoSelect;
    }

    public FuncParser getFuncParser() {
        return funcParser;
    }

}
