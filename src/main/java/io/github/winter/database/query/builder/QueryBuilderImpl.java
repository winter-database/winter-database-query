package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.*;
import io.github.winter.database.query.dto.QueryColumnDto;
import io.github.winter.database.query.dto.QueryDto;
import io.github.winter.database.query.dto.QueryJoinDto;
import io.github.winter.database.query.dto.QueryJoinOnDto;
import io.github.winter.database.query.parser.FuncParserImpl;
import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * @author changebooks@qq.com
 */
public class QueryBuilderImpl implements QueryBuilder {
    /**
     * the {@link SelectTemplate} instance
     */
    private final SelectTemplate selectTemplate;

    /**
     * the {@link FuncParser} instance
     */
    private final FuncParser funcParser;

    public QueryBuilderImpl(JdbcTemplate jdbcTemplate) {
        this.selectTemplate = new SelectTemplate(jdbcTemplate);
        this.funcParser = new FuncParserImpl();
    }

    @Override
    public Query build(int id) {
        Map<String, Value> record = selectTemplate.selectOne("xquery", Collections.singletonMap("id", id));
        QueryDto dto = QueryDto.newInstance(record);
        if (dto == null) {
            return null;
        }

        Query query = new Query();

        query.setId(dto.getId());
        query.setName(dto.getQueryName());
        query.setDistinct(dto.getDistinct());
        query.setTableName(dto.getFromTable());
        query.setPage(getPage(dto.getPageOffset(), dto.getPageLimit()));
        query.setDescription(dto.getQueryDescription());
        query.setRemark(dto.getQueryRemark());

        setColumn(query, dto.getAsterisk());
        setJoin(query);
        setFilter(query);
        setGroup(query);
        setOrder(query);
//        query.setValueTypes;

        return null;
    }

    @Override
    public void setColumn(Query query, boolean isAsterisk) {
        String tableName = query.getTableName();
        Map<String, Class<?>> valueTypes2 = new HashMap<>();

        List<String> columns = new ArrayList<>();
        if (isAsterisk) {
            TableSchema tableSchema = TableSchemaRegistry.get(tableName);
            List<String> columnNames = tableSchema.getColumnNames();
            Map<String, Class<?>> valueTypes = tableSchema.getValueTypes();
            for (String columnName : columnNames) {
                String joinedName = QueryUtils.joinName(tableName, columnName);
                columns.add(joinedName);
                valueTypes2.put(joinedName, valueTypes.get(columnName));
            }
        }

        int queryId = query.getId();
        List<Map<String, Value>> list = selectTemplate.selectList("xquery_column", Collections.singletonMap("query_id", queryId));
        List<QueryColumnDto> dtoList = QueryColumnDto.newInstance(list);
        if (dtoList == null) {
            return;
        }

        for (QueryColumnDto columnDto : dtoList) {
            String tableName2 = columnDto.getTableName();
            if (tableName2 == null || tableName2.isBlank()) {
                tableName2 = tableName;
            }

            String columnName = columnDto.getColumnName();
            TableSchema tableSchema = TableSchemaRegistry.get(tableName2);
            Map<String, Class<?>> valueTypes = tableSchema.getValueTypes();

            int func = columnDto.getFuncType();
            Class<?> aClass = valueTypes.get(columnName);
            String parsedName = funcParser.parse(func, tableName2, columnName);
            if (columns.contains(parsedName)) {
                continue;
            }

            columns.add(parsedName);
            valueTypes2.put(parsedName, aClass);
        }

        query.setColumns(columns);
        query.setValueTypes(valueTypes2);
    }

    @Override
    public void setJoin(Query query) {
        int queryId = query.getId();
        List<Map<String, Value>> list = selectTemplate.selectList("xquery_join", Collections.singletonMap("query_id", queryId));
        List<QueryJoinDto> dtoList = QueryJoinDto.newInstance(list);
        if (dtoList == null) {
            return;
        }

        List<Join> joins = new ArrayList<>();

        for (QueryJoinDto joinDto : dtoList) {
            int joinDtoId = joinDto.getId();
            Map<String, Integer> queryId1 = new HashMap<>(Collections.singletonMap("query_id", queryId));
            queryId1.put("join_id", joinDtoId);
            List<Map<String, Value>> list2 = selectTemplate.selectList("xquery_join_on", queryId1);
            List<QueryJoinOnDto> joinOnDtoList = QueryJoinOnDto.newInstance(list2);

            List<Join.On> filters = new ArrayList<>();
            for (QueryJoinOnDto queryJoinOnDto : joinOnDtoList) {
                String leftTable = queryJoinOnDto.getLeftTable();
                String leftColumn = queryJoinOnDto.getLeftColumn();
                String rightTable = queryJoinOnDto.getRightTable();
                String rightColumn = queryJoinOnDto.getRightColumn();
                Join.On on = new Join.On();
                on.setLeftName(QueryUtils.joinName(leftTable, leftColumn));
                on.setRightName(QueryUtils.joinName(rightTable, rightColumn));
                filters.add(on);
            }

            String joinTable = joinDto.getJoinTable();
            int joinType = joinDto.getJoinType();
            Join join = new Join();
            join.setFilters(filters);
            join.setTableName(joinTable);
            join.setType(joinType);

            joins.add(join);
        }

        query.setJoins(joins);
    }

    @Override
    public void setFilter(Query query) {

    }

    @Override
    public void setGroup(Query query) {

    }

    @Override
    public void setOrder(Query query) {

    }

    @Override
    public void setPage(Query query) {

    }

    @Override
    public List<BaseFilter> getFilter(int isHaving, int queryId, int parentId) {
        return null;
    }

    @Override
    public Page getPage(Long offset, Integer limit) {
        Page page = new Page();

        page.setOffset(offset);
        page.setLimit(limit);

        return page;
    }

    public SelectTemplate getSelectTemplate() {
        return selectTemplate;
    }

}
