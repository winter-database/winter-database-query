package io.github.winter.database.query.builder;

import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.entity.*;
import jakarta.validation.constraints.NotEmpty;
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
     * 查询全部
     *
     * @return [ the {@link Query} instance ]
     */
    public List<Query> selectQueryAll() {
        List<Map<String, Value>> list = selectTemplate.selectList("xquery", null);
        return Query.newInstance(list);
    }

    /**
     * 查询
     *
     * @param id 主键
     * @return the {@link Query} instance
     */
    public Query selectQuery(int id) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("id", id);

        Map<String, Value> record = selectTemplate.selectOne("xquery", filters);
        return Query.newInstance(record);
    }

    /**
     * 字段
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ the {@link QueryColumn} instance ]
     */
    public List<QueryColumn> selectColumn(int queryId, @NotEmpty String fromTable) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_column", filters);
        return QueryColumn.newInstance(list, fromTable);
    }

    /**
     * 连表
     *
     * @param queryId 查询主键
     * @return [ the {@link QueryJoin} instance ]
     */
    public List<QueryJoin> selectJoin(int queryId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_join", filters);
        return QueryJoin.newInstance(list);
    }

    /**
     * 连表条件
     *
     * @param queryId 查询主键
     * @param joinId  连表主键
     * @return [ the {@link QueryJoinOn} instance ]
     */
    public List<QueryJoinOn> selectJoinOn(int queryId, int joinId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("join_id", joinId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_join_on", filters);
        return QueryJoinOn.newInstance(list);
    }

    /**
     * 条件
     *
     * @param isHaving  分组条件？
     * @param queryId   查询主键
     * @param parentId  父条件主键
     * @param fromTable 表名
     * @return [ the {@link QueryFilter} instance ]
     */
    public List<QueryFilter> selectFilter(int isHaving, int queryId, int parentId, @NotEmpty String fromTable) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("is_having", isHaving);
        filters.put("query_id", queryId);
        filters.put("parent_id", parentId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_filter", filters);
        return QueryFilter.newInstance(list, fromTable);
    }

    /**
     * 表达式
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return the {@link QueryFilterExpression} instance
     */
    public QueryFilterExpression selectFilterExpression(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_expression", filters);
        return QueryFilterExpression.newInstance(record);
    }

    /**
     * 在列表中
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return the {@link QueryFilterIn} instance
     */
    public QueryFilterIn selectFilterIn(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_in", filters);
        return QueryFilterIn.newInstance(record);
    }

    /**
     * 列表值
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return [ the {@link QueryFilterInValue} instance ]
     */
    public List<QueryFilterInValue> selectFilterInValue(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_filter_in_value", filters);
        return QueryFilterInValue.newInstance(list);
    }

    /**
     * 空
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return the {@link QueryFilterNull} instance
     */
    public QueryFilterNull selectFilterNull(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_null", filters);
        return QueryFilterNull.newInstance(record);
    }

    /**
     * 范围
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return the {@link QueryFilterRange} instance
     */
    public QueryFilterRange selectFilterRange(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_range", filters);
        return QueryFilterRange.newInstance(record);
    }

    /**
     * 模糊匹配
     *
     * @param queryId  查询主键
     * @param filterId 条件主键
     * @return the {@link QueryFilterWildcard} instance
     */
    public QueryFilterWildcard selectFilterWildcard(int queryId, int filterId) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);
        filters.put("filter_id", filterId);

        Map<String, Value> record = selectTemplate.selectOne("xquery_filter_wildcard", filters);
        return QueryFilterWildcard.newInstance(record);
    }

    /**
     * 分组
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ the {@link QueryGroup} instance ]
     */
    public List<QueryGroup> selectGroup(int queryId, @NotEmpty String fromTable) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_group", filters);
        return QueryGroup.newInstance(list, fromTable);
    }

    /**
     * 排序
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ the {@link QueryOrder} instance ]
     */
    public List<QueryOrder> selectOrder(int queryId, @NotEmpty String fromTable) {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("query_id", queryId);

        List<Map<String, Value>> list = selectTemplate.selectList("xquery_order", filters);
        return QueryOrder.newInstance(list, fromTable);
    }

    public SelectTemplate getSelectTemplate() {
        return selectTemplate;
    }

}
