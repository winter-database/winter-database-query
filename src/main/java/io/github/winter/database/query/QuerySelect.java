package io.github.winter.database.query;

import io.github.winter.boot.tuple.Pair;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.dto.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;
import java.util.function.Predicate;

/**
 * Query Select
 *
 * @author changebooks@qq.com
 */
public class QuerySelect {
    /**
     * the {@link SelectTemplate} instance
     */
    private final SelectTemplate selectTemplate;

    public QuerySelect(JdbcTemplate jdbcTemplate) {
        this.selectTemplate = new SelectTemplate(jdbcTemplate);
    }

    /**
     * 查询
     *
     * @param id 主键
     * @return the {@link QueryDto} instance
     */
    public QueryDto selectQuery(int id) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("id", id);

        Map<String, Value> record = selectTemplate.selectOne("xquery", filters);
        return QueryDto.newInstance(record);
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
        List<QueryColumnDto> list = selectQueryColumn(queryId);
        if (list == null) {
            return Pair.of(null, null);
        }

        List<String> columns = new ArrayList<>();
        Map<String, Class<?>> valueTypes = new HashMap<>();

        for (QueryColumnDto record : list) {
            if (record == null) {
                continue;
            }

            int func = record.getFuncType();
            if (func == FuncType.COUNT) {
                columns.add(FuncParser.COUNT);
                valueTypes.put(FuncParser.COUNT, Long.class);
                continue;
            }

            String tableName = Optional.of(record.getTableName()).filter(Predicate.not(String::isEmpty)).orElse(fromTable);
            String columnName = record.getColumnName();
            String column = FuncParser.parse(func, tableName, columnName);
            if (column == null || column.isEmpty()) {
                continue;
            }

            Class<?> valueType = QueryUtils.getValueType(tableName, columnName);
            if (valueType == null) {
                continue;
            }

            columns.add(column);
            valueTypes.put(column, valueType);
        }

        return Pair.of(columns, valueTypes);
    }

    /**
     * 字段
     *
     * @param queryId 查询主键
     * @return [ the {@link QueryColumnDto} instance ]
     */
    public List<QueryColumnDto> selectQueryColumn(int queryId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_column", filters);
        return QueryColumnDto.newInstance(list);
    }

    /**
     * 连表
     *
     * @param queryId 查询主键
     * @return [ the {@link Join} instance ]
     */
    public List<Join> selectJoin(int queryId) {
        List<QueryJoinDto> list = selectQueryJoin(queryId);
        if (list == null) {
            return null;
        }

        List<Join> result = new ArrayList<>();

        for (QueryJoinDto record : list) {
            if (record == null) {
                continue;
            }

            Join join = new Join();

            String tableName = record.getJoinTable();
            join.setTableName(tableName);

            int type = record.getJoinType();
            join.setType(type);

            int joinId = record.getId();
            List<Join.On> filters = selectJoinOn(queryId, joinId);
            join.setFilters(filters);

            result.add(join);
        }

        return result;
    }

    /**
     * 连表
     *
     * @param queryId 查询主键
     * @return [ the {@link QueryJoinDto} instance ]
     */
    public List<QueryJoinDto> selectQueryJoin(int queryId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_join", filters);
        return QueryJoinDto.newInstance(list);
    }

    /**
     * 连表条件
     *
     * @param queryId 查询主键
     * @param joinId  连表主键
     * @return [ the {@link Join.On} instance ]
     */
    public List<Join.On> selectJoinOn(int queryId, int joinId) {
        List<QueryJoinOnDto> list = selectQueryJoinOn(queryId, joinId);
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

            String leftName = QueryUtils.joinName(leftTable, leftColumn);
            String rightName = QueryUtils.joinName(rightTable, rightColumn);

            Join.On on = new Join.On();
            on.setLeftName(leftName);
            on.setRightName(rightName);

            result.add(on);
        }

        return result;
    }

    /**
     * 连表条件
     *
     * @param queryId 查询主键
     * @param joinId  连表主键
     * @return [ the {@link QueryJoinOnDto} instance ]
     */
    public List<QueryJoinOnDto> selectQueryJoinOn(int queryId, int joinId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("join_id", joinId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_join_on", filters);
        return QueryJoinOnDto.newInstance(list);
    }

    /**
     * 条件
     *
     * @param isHaving 分组条件？
     * @param queryId  查询主键
     * @param parentId 父条件主键
     * @return [ the {@link QueryFilterDto} instance ]
     */
    public List<QueryFilterDto> selectQueryFilter(int isHaving, int queryId, int parentId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("is_having", isHaving);
        filters.put("query_id", queryId);
        filters.put("parent_id", parentId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_filter", filters);
        return QueryFilterDto.newInstance(list);
    }

    /**
     * 表达式
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return the {@link QueryFilterExpressionDto} instance
     */
    public QueryFilterExpressionDto selectQueryFilterExpression(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_expression", filters);
        return QueryFilterExpressionDto.newInstance(record);
    }

    /**
     * 在列表中
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return the {@link QueryFilterInDto} instance
     */
    public QueryFilterInDto selectQueryFilterIn(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_in", filters);
        return QueryFilterInDto.newInstance(record);
    }

    /**
     * 列表值
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return [ the {@link QueryFilterInValueDto} instance ]
     */
    public List<QueryFilterInValueDto> selectQueryFilterInValue(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_filter_in_value", filters);
        return QueryFilterInValueDto.newInstance(list);
    }

    /**
     * 空
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return the {@link QueryFilterNullDto} instance
     */
    public QueryFilterNullDto selectQueryFilterNull(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_null", filters);
        return QueryFilterNullDto.newInstance(record);
    }

    /**
     * 范围
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return the {@link QueryFilterRangeDto} instance
     */
    public QueryFilterRangeDto selectQueryFilterRange(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_range", filters);
        return QueryFilterRangeDto.newInstance(record);
    }

    /**
     * 模糊匹配
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return the {@link QueryFilterWildcardDto} instance
     */
    public QueryFilterWildcardDto selectQueryFilterWildcard(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_wildcard", filters);
        return QueryFilterWildcardDto.newInstance(record);
    }

    /**
     * 分组
     *
     * @param queryId 查询主键
     * @return [ the {@link QueryGroupDto} instance ]
     */
    public List<QueryGroupDto> selectQueryGroup(int queryId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_group", filters);
        return QueryGroupDto.newInstance(list);
    }

    /**
     * 排序
     *
     * @param queryId 查询主键
     * @return [ the {@link QueryOrderDto} instance ]
     */
    public List<QueryOrderDto> selectQueryOrder(int queryId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_order", filters);
        return QueryOrderDto.newInstance(list);
    }

    public SelectTemplate getSelectTemplate() {
        return selectTemplate;
    }

}
