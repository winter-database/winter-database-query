package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.tuple.Pair;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

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
