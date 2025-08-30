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
public class FilterRangeReader {

    public Entity.FilterRange read(@NotNull Template template, int queryId, int filterId) throws SQLException {
        List<BaseFilter> filters = new ArrayList<>(Collections.singletonList(getRecycleFilter()));
        filters.add(getQueryIdFilter(queryId));
        filters.add(getFilterIdFilter(filterId));

        Map<String, Value> valueMap = template.selectOne("xquery_filter_range", filters, null, null);

        Value isIncludeLower = valueMap.get("is_include_lower");
        Value isIncludeUpper = valueMap.get("is_include_upper");
        Value fromValueString = valueMap.get("from_value_string");
        Value fromValueInteger = valueMap.get("from_value_integer");
        Value fromValueLong = valueMap.get("from_value_long");
        Value fromValueBigDecimal = valueMap.get("from_value_big_decimal");
        Value fromValueDate = valueMap.get("from_value_date");
        Value toValueString = valueMap.get("to_value_string");
        Value toValueInteger = valueMap.get("to_value_integer");
        Value toValueLong = valueMap.get("to_value_long");
        Value toValueBigDecimal = valueMap.get("to_value_big_decimal");
        Value toValueDate = valueMap.get("to_value_date");

        Entity.FilterRange result = new Entity.FilterRange();

        result.setIncludeLower(isIncludeLower.getInteger() == 1);
        result.setIncludeUpper(isIncludeUpper.getInteger() == 1);
        result.setFromValueString(fromValueString.getString());
        result.setFromValueInteger(fromValueInteger.getInteger());
        result.setFromValueLong(fromValueLong.getLong());
        result.setFromValueBigDecimal(fromValueBigDecimal.getBigDecimal());
        result.setFromValueDate(fromValueDate.getDate());
        result.setToValueString(toValueString.getString());
        result.setToValueInteger(toValueInteger.getInteger());
        result.setToValueLong(toValueLong.getLong());
        result.setToValueBigDecimal(toValueBigDecimal.getBigDecimal());
        result.setToValueDate(toValueDate.getDate());

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
