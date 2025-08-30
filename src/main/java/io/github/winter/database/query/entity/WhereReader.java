package io.github.winter.database.query.entity;

import io.github.winter.boot.filter.*;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.Entity;
import io.github.winter.database.query.FilterType;
import io.github.winter.database.template.Template;
import jakarta.validation.constraints.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 条件
 *
 * @author changebooks@qq.com
 */
public class WhereReader {

    public List<Entity.Filter> read(@NotNull Template template, int queryId, int parentId) throws SQLException {
        WhereReader whereReader = new WhereReader();

        List<BaseFilter> filters = new ArrayList<>(Collections.singletonList(getRecycleFilter()));
        filters.add(getQueryIdFilter(queryId));
        filters.add(getParentIdFilter(parentId));

        List<Order> orders = Collections.singletonList(getOrder());

        List<Entity.Filter> result = new ArrayList<>();

        List<Map<String, Value>> list = template.selectList("xquery_filter", filters, orders, null, null);
        for (Map<String, Value> valueMap : list) {

            Value id = valueMap.get("id");
            Value tableName = valueMap.get("table_name");
            Value columnName = valueMap.get("column_name");
            Value funcType = valueMap.get("func_type");
            Value filterType = valueMap.get("filter_type");
            Value logicalOperator = valueMap.get("logical_operator");

            List<Entity.Filter> filters1 = whereReader.read(template, queryId, id.getInteger());

            Entity.Filter record = new Entity.Filter();

            record.setTableName(tableName.getString());
            record.setColumnName(columnName.getString());
            record.setFuncType(funcType.getInteger());
            record.setFilterType(filterType.getInteger());
            record.setLogicalOperator(logicalOperator.getInteger());
            record.setFilters(filters1);

            if (FilterType.EXPRESSION == filterType.getInteger()) {
                FilterExpressionReader filterExpressionReader = new FilterExpressionReader();
                Entity.FilterExpression filterExpression = filterExpressionReader.read(template, queryId, id.getInteger());
                record.setFilterExpression(filterExpression);
            }

            if (FilterType.IN == filterType.getInteger()) {
                FilterInReader filterInReader = new FilterInReader();
                Entity.FilterIn filterIn = filterInReader.read(template, queryId, id.getInteger());
                record.setFilterIn(filterIn);
            }

            if (FilterType.NULL == filterType.getInteger()) {
                FilterNullReader filterInReader = new FilterNullReader();
                Entity.FilterNull filterNull = filterInReader.read(template, queryId, id.getInteger());
                record.setFilterNull(filterNull);
            }

            if (FilterType.RANGE == filterType.getInteger()) {
                FilterRangeReader filterRangeReader = new FilterRangeReader();
                Entity.FilterRange filterRange = filterRangeReader.read(template, queryId, id.getInteger());
                record.setFilterRange(filterRange);
            }

            if (FilterType.WILDCARD == filterType.getInteger()) {
                FilterWildcardReader filterWildcardReader = new FilterWildcardReader();
                Entity.FilterWildcard filterWildcard = filterWildcardReader.read(template, queryId, id.getInteger());
                record.setFilterWildcard(filterWildcard);
            }

            result.add(record);
        }

        return result;
    }

    public ExpressionFilter getRecycleFilter() {
        String columnName = "internal_recycle";

        Parameter parameter = new Parameter();
        parameter.setName(columnName);
        parameter.setValue(new Value(0));

        ExpressionFilter filter = new ExpressionFilter();

        filter.setCode(ExpressionCode.EQ);
        filter.setName(columnName);
        filter.setParameter(parameter);

        return filter;
    }

    public ExpressionFilter getQueryIdFilter(int queryId) {
        String columnName = "query_id";

        Parameter parameter = new Parameter();
        parameter.setName(columnName);
        parameter.setValue(new Value(queryId));

        ExpressionFilter filter = new ExpressionFilter();

        filter.setCode(ExpressionCode.EQ);
        filter.setName(columnName);
        filter.setParameter(parameter);

        return filter;
    }

    public ExpressionFilter getParentIdFilter(int parentId) {
        String columnName = "parent_id";

        Parameter parameter = new Parameter();
        parameter.setName(columnName);
        parameter.setValue(new Value(parentId));

        ExpressionFilter filter = new ExpressionFilter();

        filter.setCode(ExpressionCode.EQ);
        filter.setName(columnName);
        filter.setParameter(parameter);

        return filter;
    }

    public Order getOrder() {
        Order order = new Order();
        order.setName("show_priority");
        return order;
    }

}
