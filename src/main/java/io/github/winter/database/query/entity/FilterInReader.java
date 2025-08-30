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
 * 条件
 *
 * @author changebooks@qq.com
 */
public class FilterInReader {

    public Entity.FilterIn read(@NotNull Template template, int queryId, int filterId) throws SQLException {
        List<BaseFilter> filters = new ArrayList<>(Collections.singletonList(getRecycleFilter()));
        filters.add(getQueryIdFilter(queryId));
        filters.add(getFilterIdFilter(filterId));

        Map<String, Value> valueMap = template.selectOne("xquery_filter_in", filters, null, null);

        Value isNot = valueMap.get("is_not");

        List<Order> orders = Collections.singletonList(getOrder());
        List<Map<String, Value>> list = template.selectList("xquery_filter_in_value", filters, orders, null, null);

        List<Entity.FilterInValue> inValues = new ArrayList<>();

        for (Map<String, Value> valueMap2 : list) {
            Value valueString = valueMap2.get("value_string");
            Value valueInteger = valueMap2.get("value_integer");
            Value valueLong = valueMap2.get("value_long");
            Value valueBigDecimal = valueMap2.get("value_big_decimal");
            Value valueDate = valueMap2.get("value_date");

            Entity.FilterInValue inValue = new Entity.FilterInValue();
            inValue.setValueString(valueString.getString());
            inValue.setValueInteger(valueInteger.getInteger());
            inValue.setValueLong(valueLong.getLong());
            inValue.setValueBigDecimal(valueBigDecimal.getBigDecimal());
            inValue.setValueDate(valueDate.getDate());

            inValues.add(inValue);
        }

        Entity.FilterIn result = new Entity.FilterIn();
        result.setNot(isNot.getInteger() == 1);
        result.setValues(inValues);

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

    public ExpressionFilter getFilterIdFilter(int parentId) {
        String columnName = "filter_id";

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
