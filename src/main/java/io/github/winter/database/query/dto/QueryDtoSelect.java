package io.github.winter.database.query.dto;

import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.BooleanCast;
import io.github.winter.database.query.SelectTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Query Dto Select
 *
 * @author changebooks@qq.com
 */
public class QueryDtoSelect {
    /**
     * the {@link SelectTemplate} instance
     */
    private final SelectTemplate selectTemplate;

    public QueryDtoSelect(JdbcTemplate jdbcTemplate) {
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
     * @param queryId 查询主键
     * @return [ the {@link QueryColumnDto} instance ]
     */
    public List<QueryColumnDto> selectColumn(int queryId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_column", filters);
        return QueryColumnDto.newInstance(list);
    }

    /**
     * 连表
     *
     * @param queryId 查询主键
     * @return [ the {@link QueryJoinDto} instance ]
     */
    public List<QueryJoinDto> selectJoin(int queryId) {
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
     * @return [ the {@link QueryJoinOnDto} instance ]
     */
    public List<QueryJoinOnDto> selectJoinOn(int queryId, int joinId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("join_id", joinId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_join_on", filters);
        return QueryJoinOnDto.newInstance(list);
    }

    /**
     * 查询条件
     *
     * @param queryId  查询主键
     * @param parentId 父条件主键
     * @return [ the {@link QueryFilterDto} instance ]
     */
    public List<QueryFilterDto> selectWhere(int queryId, int parentId) {
        return selectFilter(BooleanCast.FALSE, queryId, parentId);
    }

    /**
     * 分组
     *
     * @param queryId 查询主键
     * @return [ the {@link QueryGroupDto} instance ]
     */
    public List<QueryGroupDto> selectGroup(int queryId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_group", filters);
        return QueryGroupDto.newInstance(list);
    }

    /**
     * 分组条件
     *
     * @param queryId  查询主键
     * @param parentId 父条件主键
     * @return [ the {@link QueryFilterDto} instance ]
     */
    public List<QueryFilterDto> selectHaving(int queryId, int parentId) {
        return selectFilter(BooleanCast.TRUE, queryId, parentId);
    }

    /**
     * 排序
     *
     * @param queryId 查询主键
     * @return [ the {@link QueryOrderDto} instance ]
     */
    public List<QueryOrderDto> selectOrder(int queryId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_order", filters);
        return QueryOrderDto.newInstance(list);
    }

    /**
     * 条件
     *
     * @param isHaving 分组条件？
     * @param queryId  查询主键
     * @param parentId 父条件主键
     * @return [ the {@link QueryFilterDto} instance ]
     */
    public List<QueryFilterDto> selectFilter(int isHaving, int queryId, int parentId) {
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
    public QueryFilterExpressionDto selectFilterExpression(int queryId, int filterId) {
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
    public QueryFilterInDto selectFilterIn(int queryId, int filterId) {
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
    public List<QueryFilterInValueDto> selectFilterInValue(int queryId, int filterId) {
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
    public QueryFilterNullDto selectFilterNull(int queryId, int filterId) {
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
    public QueryFilterRangeDto selectFilterRange(int queryId, int filterId) {
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
    public QueryFilterWildcardDto selectFilterWildcard(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_wildcard", filters);
        return QueryFilterWildcardDto.newInstance(record);
    }

    public SelectTemplate getSelectTemplate() {
        return selectTemplate;
    }

}
