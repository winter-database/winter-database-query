package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.*;
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
import java.util.function.Predicate;

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

        int queryId = record.getId();
        String queryName = record.getQueryName();
        boolean distinct = record.getDistinct();
        boolean asterisk = record.getAsterisk();
        String fromTable = record.getFromTable();
        Long pageOffset = record.getPageOffset();
        Integer pageLimit = record.getPageLimit();
        String queryDescription = record.getQueryDescription();
        String queryRemark = record.getQueryRemark();

        Page page = new Page();
        page.setOffset(pageOffset);
        page.setLimit(pageLimit);

        Query result = new Query();

        result.setId(queryId);
        result.setName(queryName);
        result.setDistinct(distinct);
        result.setTableName(fromTable);
        result.setDescription(queryDescription);
        result.setRemark(queryRemark);
        result.setPage(page);

        if (asterisk) {
            Pair<List<String>, Map<String, Class<?>>> asteriskPair = AsteriskBuilder.build(fromTable);
            result.setColumns(asteriskPair.first);
            result.setValueTypes(asteriskPair.second);
        }

//        List<String> columns;
//        List<Join> joins;
//        List<BaseFilter> filters;
//        Group group;
//        List<Order> orders;
//        Map<String, Class<?>> valueTypes;

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

        List<String> columns = new ArrayList<>();
        Map<String, Class<?>> valueTypes = new HashMap<>();

        for (QueryColumnDto record : list) {
            if (record == null) {
                continue;
            }

            int funcType = record.getFuncType();
            if (funcType == FuncType.COUNT) {
                columns.add(FuncParser.COUNT);
                valueTypes.put(FuncParser.COUNT, Long.class);
                continue;
            }

            String tableName = Optional.of(record.getTableName()).filter(Predicate.not(String::isEmpty)).orElse(fromTable);
            String columnName = record.getColumnName();
            String column = funcParser.parse(funcType, tableName, columnName);
            if (column == null || column.isEmpty()) {
                continue;
            }

            Class<?> valueType = ValueTypeGetter.get(tableName, columnName);
            if (valueType == null) {
                continue;
            }

            columns.add(column);
            valueTypes.put(column, valueType);
        }

        return Pair.of(columns, valueTypes);
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
            int joinType = record.getJoinType();
            int joinId = record.getId();
            List<Join.On> filters = selectJoinOn(queryId, joinId);

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
            String rightName = NameJoiner.join(rightTable, rightColumn);

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

        List<String> columns = new ArrayList<>();

        for (QueryGroupDto record : list) {
            if (record == null) {
                continue;
            }

            String tableName = Optional.of(record.getTableName()).filter(Predicate.not(String::isEmpty)).orElse(fromTable);
            String columnName = record.getColumnName();
            String column = NameJoiner.join(tableName, columnName);
            if (column.isEmpty()) {
                continue;
            }

            columns.add(column);
        }

        List<BaseFilter> filters = selectFilter(BooleanCast.FALSE, queryId, 0, fromTable);

        Group result = new Group();

        result.setNames(columns);
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
            String tableName = Optional.of(record.getTableName()).filter(Predicate.not(String::isEmpty)).orElse(fromTable);
            String columnName = record.getColumnName();
            String column = funcParser.parse(funcType, tableName, columnName);
            if (column == null || column.isEmpty()) {
                continue;
            }

            int orderType = record.getOrderType();
            Boolean desc = OrderType.isDesc(orderType);

            Order order = new Order();

            order.setName(column);
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

            int filterId = record.getId();
            int filterType = record.getFilterType();
            int funcType = record.getFuncType();
            String tableName = Optional.of(record.getTableName()).filter(Predicate.not(String::isEmpty)).orElse(fromTable);
            String columnName = record.getColumnName();
            int logicalOperator = record.getLogicalOperator();
            Boolean or = LogicalType.isOr(logicalOperator);

            String filterName = funcParser.parse(funcType, tableName, columnName);
            Class<?> valueType = ValueTypeGetter.get(tableName, columnName);

            if (filterType == FilterType.EXPRESSION) {
                QueryFilterExpressionDto filterExpression = queryDtoSelect.selectFilterExpression(queryId, filterId);
                if (filterExpression == null) {
                    continue;
                }

                int expressionCode = filterExpression.getExpressionCode();

                String valueString = filterExpression.getValueString();
                Integer valueInteger = filterExpression.getValueInteger();
                Long valueLong = filterExpression.getValueLong();
                BigDecimal valueBigDecimal = filterExpression.getValueBigDecimal();
                Date valueDate = filterExpression.getValueDate();
                Value value = ValueBuilder.build(valueType, valueString, valueInteger, valueLong, valueBigDecimal, valueDate);

                Parameter parameter = new Parameter();
                parameter.setValue(value);

                ExpressionFilter expressionFilter = new ExpressionFilter();
                expressionFilter.setOr(or);
                expressionFilter.setName(filterName);
                expressionFilter.setCode(expressionCode);
                expressionFilter.setParameter(parameter);

                result.add(expressionFilter);
                continue;
            }

            if (filterType == FilterType.IN) {
                QueryFilterInDto filterIn = queryDtoSelect.selectFilterIn(queryId, filterId);
                if (filterIn == null) {
                    continue;
                }

                List<QueryFilterInValueDto> filterInValueList = queryDtoSelect.selectFilterInValue(queryId, filterId);
                if (filterInValueList == null) {
                    continue;
                }

                Boolean not = filterIn.getNot();
                List<Parameter> parameters = new ArrayList<>();

                for (QueryFilterInValueDto filterInValueRecord : filterInValueList) {
                    if (filterInValueRecord == null) {
                        continue;
                    }

                    String valueString = filterInValueRecord.getValueString();
                    Integer valueInteger = filterInValueRecord.getValueInteger();
                    Long valueLong = filterInValueRecord.getValueLong();
                    BigDecimal valueBigDecimal = filterInValueRecord.getValueBigDecimal();
                    Date valueDate = filterInValueRecord.getValueDate();
                    Value value = ValueBuilder.build(valueType, valueString, valueInteger, valueLong, valueBigDecimal, valueDate);

                    Parameter parameter = new Parameter();
                    parameter.setValue(value);

                    parameters.add(parameter);
                }

                InFilter inFilter = new InFilter();

                inFilter.setName(filterName);
                inFilter.setNot(not);
                inFilter.setParameters(parameters);

                result.add(inFilter);
                continue;
            }

            if (filterType == FilterType.NULL) {
                QueryFilterNullDto filterNull = queryDtoSelect.selectFilterNull(queryId, filterId);
                if (filterNull == null) {
                    continue;
                }

                NullFilter nullFilter = new NullFilter();

                nullFilter.setNot(filterNull.getNot());
                nullFilter.setName(filterName);
                nullFilter.setOr(or);

                result.add(nullFilter);
                continue;
            }

            if (filterType == FilterType.RANGE) {
                QueryFilterRangeDto filterRange = queryDtoSelect.selectFilterRange(queryId, filterId);
                if (filterRange == null) {
                    continue;
                }

                Boolean isIncludeLower = filterRange.getIncludeLower();
                Boolean isIncludeUpper = filterRange.getIncludeUpper();
                String fromValueString = filterRange.getFromValueString();
                Integer fromValueInteger = filterRange.getFromValueInteger();
                Long fromValueLong = filterRange.getFromValueLong();
                BigDecimal fromValueBigDecimal = filterRange.getFromValueBigDecimal();
                Date fromValueDate = filterRange.getFromValueDate();
                String toValueString = filterRange.getToValueString();
                Integer toValueInteger = filterRange.getToValueInteger();
                Long toValueLong = filterRange.getToValueLong();
                BigDecimal toValueBigDecimal = filterRange.getToValueBigDecimal();
                Date toValueDate = filterRange.getToValueDate();

                Value fromValue = ValueBuilder.build(valueType, fromValueString, fromValueInteger, fromValueLong, fromValueBigDecimal, fromValueDate);
                Value toValue = ValueBuilder.build(valueType, toValueString, toValueInteger, toValueLong, toValueBigDecimal, toValueDate);

                Parameter from = new Parameter();
                from.setValue(fromValue);

                Parameter to = new Parameter();
                to.setValue(toValue);

                RangeFilter rangeFilter = new RangeFilter();

                rangeFilter.setFrom(from);
                rangeFilter.setTo(to);
                rangeFilter.setIncludeLower(isIncludeLower);
                rangeFilter.setIncludeUpper(isIncludeUpper);

                result.add(rangeFilter);
                continue;
            }

            if (filterType == FilterType.WILDCARD) {
                QueryFilterWildcardDto filterWildcard = queryDtoSelect.selectFilterWildcard(queryId, filterId);
                if (filterWildcard == null) {
                    continue;
                }

                Boolean isNot = filterWildcard.getNot();
                int wildcardCode = filterWildcard.getWildcardCode();
                String valueString = filterWildcard.getValueString();
                Integer valueInteger = filterWildcard.getValueInteger();
                Long valueLong = filterWildcard.getValueLong();
                BigDecimal valueBigDecimal = filterWildcard.getValueBigDecimal();
                Date valueDate = filterWildcard.getValueDate();
                Value value = ValueBuilder.build(valueType, valueString, valueInteger, valueLong, valueBigDecimal, valueDate);

                Parameter parameter = new Parameter();
                parameter.setValue(value);

                WildcardFilter wildcardFilter = new WildcardFilter();
                wildcardFilter.setOr(or);
                wildcardFilter.setName(filterName);
                wildcardFilter.setNot(isNot);
                wildcardFilter.setCode(wildcardCode);
                wildcardFilter.setParameter(parameter);

                result.add(wildcardFilter);
                continue;
            }

            Filters filters = new Filters();
            filters.setFilters(selectFilter(isHaving, queryId, filterId, fromTable));

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
