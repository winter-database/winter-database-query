package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.boot.tuple.Pair;
import io.github.winter.database.query.*;
import io.github.winter.database.query.dto.QueryDto;
import io.github.winter.database.query.dto.QueryDtoSelect;
import io.github.winter.database.query.parser.FuncParserImpl;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author changebooks@qq.com
 */
public class QueryBuilderImpl implements QueryBuilder {
    /**
     * the {@link QueryDtoSelect} instance
     */
    private final QueryDtoSelect queryDtoSelect;

    /**
     * the {@link FuncParser} instance
     */
    private final FuncParser funcParser;

    public QueryBuilderImpl(JdbcTemplate jdbcTemplate) {
        this.queryDtoSelect = new QueryDtoSelect(jdbcTemplate);
        this.funcParser = new FuncParserImpl();
    }

    @Override
    public List<Integer> selectIds() {
        List<Integer> result = new ArrayList<>();

        List<QueryDto> list = queryDtoSelect.selectQueryAll();
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
        return null;
    }

    @Override
    public Pair<List<String>, Map<String, Class<?>>> selectColumn(int queryId, String fromTable) {
        return null;
    }

    @Override
    public List<Join> selectJoin(int queryId) {
        return null;
    }

    @Override
    public List<Join.On> selectJoinOn(int queryId, int joinId) {
        return null;
    }

    @Override
    public Group selectGroup(int queryId, String fromTable) {
        return null;
    }

    @Override
    public List<Order> selectOrder(int queryId, String fromTable) {
        return null;
    }

    @Override
    public List<BaseFilter> selectFilter(int isHaving, int queryId, int parentId, String fromTable) {
        return null;
    }

    @Override
    public String parseFunc(int func, String tableName, String columnName) {
        return funcParser.parse(func, tableName, columnName);
    }

    @Override
    public String parseFunc(int func, String name) {
        return funcParser.parse(func, name);
    }

    public QueryDtoSelect getQueryDtoSelect() {
        return queryDtoSelect;
    }

    public FuncParser getFuncParser() {
        return funcParser;
    }

}
