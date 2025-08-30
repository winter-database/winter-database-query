package io.github.winter.database.query.entity;

import io.github.winter.boot.filter.*;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.Entity;
import io.github.winter.database.template.Template;
import jakarta.validation.constraints.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 字段描述
 *
 * @author changebooks@qq.com
 */
public class ColumnReader {

    public List<Entity.Column> read(@NotNull Template template, int queryId) throws SQLException {
        List<BaseFilter> filters = new java.util.ArrayList<>(Collections.singletonList(getRecycleFilter()));
        filters.add(getQueryIdFilter(queryId));

        List<Order> orders = Collections.singletonList(getOrder());

        List<Entity.Column> result = new ArrayList<>();

        List<Map<String, Value>> list = template.selectList("xquery_column", filters, orders, null, null);
        for (Map<String, Value> valueMap : list) {

            Value tableName = valueMap.get("table_name");
            Value columnName = valueMap.get("column_name");
            Value funcType = valueMap.get("func_type");

            Entity.Column record = new Entity.Column();
            record.setTableName(tableName.getString());
            record.setColumnName(columnName.getString());
            record.setFuncType(funcType.getInteger());

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

    public Order getOrder() {
        Order order = new Order();
        order.setName("show_priority");
        return order;
    }

}
