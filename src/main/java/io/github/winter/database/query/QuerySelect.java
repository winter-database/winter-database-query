package io.github.winter.database.query;

import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.dto.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
