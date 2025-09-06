package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.tuple.Pair;
import io.github.winter.database.query.dto.QueryColumnDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;
import java.util.function.Predicate;

/**
 * Query Builder
 *
 * @author changebooks@qq.com
 */
public class QueryBuilder {
    /**
     * the {@link QuerySelect} instance
     */
    private final QuerySelect querySelect;

    public QueryBuilder(JdbcTemplate jdbcTemplate) {
        this.querySelect = new QuerySelect(jdbcTemplate);
    }

    /**
     * Build
     */
    public Query build() {
        return null;
    }

    /**
     * 全字段
     */
    public void setAsterisk(Query query) {
        String tableName = query.getTableName();
        Pair<List<String>, Map<String, Class<?>>> asteriskPair = QueryUtils.getAsterisk(tableName);
        query.setColumns(asteriskPair.first);
        query.setValueTypes(asteriskPair.second);
    }

    /**
     * 字段列表
     */
    public void setColumns(Query query) {
        List<QueryColumnDto> list = querySelect.selectQueryColumn(query.getId());
        if (list == null) {
            return;
        }


    }

    /**
     * 获取字段列表
     *
     * @param queryId 查询主键
     * @return [ [ 字段名 ] : [ 字段名 : 字段类型 ] ]
     */
    @NotNull
    public Pair<List<String>, Map<String, Class<?>>> getColumns(int queryId, @NotEmpty String fromTable) {
        return null;
    }

    /**
     * 连表列表
     */
    public void setJoins(Query query) {

    }

    /**
     * 条件列表
     */
    public void setFilters(Query query) {

    }

    /**
     * 分组
     */
    public void setGroup(Query query) {

    }

    /**
     * 排序列表
     */
    public void setOrders(Query query) {

    }

    /**
     * 分页
     */
    public Page getPage(Long offset, Integer limit) {
        Page page = new Page();

        page.setOffset(offset);
        page.setLimit(limit);

        return page;
    }

    /**
     * 条件列表
     */
    public List<BaseFilter> getFilters(int isHaving, int queryId, int parentId) {

    }

    public QuerySelect getQuerySelect() {
        return querySelect;
    }

}
