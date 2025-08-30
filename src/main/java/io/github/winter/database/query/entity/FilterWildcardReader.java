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
public class FilterWildcardReader {

    public Entity.FilterWildcard read(@NotNull Template template, int queryId, int filterId) throws SQLException {
        List<BaseFilter> filters = new ArrayList<>(Collections.singletonList(getRecycleFilter()));
        filters.add(getQueryIdFilter(queryId));
        filters.add(getFilterIdFilter(filterId));

        Map<String, Value> valueMap = template.selectOne("xquery_filter_wildcard", filters, null, null);

        Value isNot = valueMap.get("is_not");
        Value wildcardCode = valueMap.get("wildcard_code");
        Value valueString = valueMap.get("value_string");
        Value valueInteger = valueMap.get("value_integer");
        Value valueLong = valueMap.get("value_long");
        Value valueBigDecimal = valueMap.get("value_big_decimal");
        Value valueDate = valueMap.get("value_date");

        Entity.FilterWildcard result = new Entity.FilterWildcard();

        result.setNot(isNot.getInteger() == 1);
        result.setWildcardCode(wildcardCode.getInteger());
        result.setValueString(valueString.getString());
        result.setValueInteger(valueInteger.getInteger());
        result.setValueLong(valueLong.getLong());
        result.setValueBigDecimal(valueBigDecimal.getBigDecimal());
        result.setValueDate(valueDate.getDate());

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
