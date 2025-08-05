package io.github.winter.database.query;

import io.github.winter.boot.filter.*;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析命令
 *
 * @author changebooks@qq.com
 */
public final class QueryOrderParser {

    public static Order parseOrder(QueryOrder queryOrder) {
        QueryColumn column = queryOrder.getColumn();
        Boolean desc = queryOrder.getDesc();

        Order order = new Order();
        order.setName(column.getName());
        order.setDesc(desc);

        return order;
    }

    public static ExpressionFilter parseExpressionFilter(QueryFilterExpression queryFilterExpression) {
        Boolean or = queryFilterExpression.getOr();
        String name = queryFilterExpression.getColumn().getName();
        int code = queryFilterExpression.getCode();
        Parameter parameter = queryFilterExpression.getParameter().getParameter();

        ExpressionFilter filter = new ExpressionFilter();
        filter.setOr(or);
        filter.setName(name);
        filter.setCode(code);
        filter.setParameter(parameter);

        return filter;
    }

    public static InFilter parseInFilter(QueryFilterIn queryFilterIn) {
        Boolean or = queryFilterIn.getOr();
        String name = queryFilterIn.getColumn().getName();
        Boolean not = queryFilterIn.getNot();
        List<Parameter> parameters = new ArrayList<>();
        String parameterName = queryFilterIn.getParameterName();
        List<Value> values = queryFilterIn.getValues();
        int i = 0;
        for (Value value : values) {
            Parameter parameter = new Parameter();
            parameter.setName(parameterName + "_" + i);
            parameter.setValue(value);
            parameters.add(parameter);
        }

        InFilter filter = new InFilter();
        filter.setOr(or);
        filter.setName(name);
        filter.setNot(not);
        filter.setParameters(parameters);

        return filter;
    }

    public static NullFilter parseNullFilter(QueryFilterNull queryFilterNull) {
        Boolean or = queryFilterNull.getOr();
        String name = queryFilterNull.getColumn().getName();
        Boolean not = queryFilterNull.getNot();

        NullFilter nullFilter = new NullFilter();
        nullFilter.setOr(or);
        nullFilter.setName(name);
        nullFilter.setNot(not);
        return nullFilter;
    }

    public static RangeFilter parseRangeFilter(QueryFilterRange queryFilterRange) {
        Boolean or = queryFilterRange.getOr();
        String name = queryFilterRange.getColumn().getName();
        Parameter from = queryFilterRange.getFrom().getParameter();
        Parameter to = queryFilterRange.getTo().getParameter();
        Boolean includeLower = queryFilterRange.getIncludeLower();
        Boolean includeUpper = queryFilterRange.getIncludeUpper();

        RangeFilter rangeFilter = new RangeFilter();
        rangeFilter.setOr(or);
        rangeFilter.setName(name);
        rangeFilter.setFrom(from);
        rangeFilter.setTo(to);
        rangeFilter.setIncludeLower(includeLower);
        rangeFilter.setIncludeUpper(includeUpper);

        return rangeFilter;
    }

    public static WildcardFilter parseWildcardFilter(QueryFilterWildcard queryFilterWildcard) {
        Boolean or = queryFilterWildcard.getOr();
        String name = queryFilterWildcard.getColumn().getName();
        Boolean not = queryFilterWildcard.getNot();
        int code = queryFilterWildcard.getCode();
        Parameter parameter = queryFilterWildcard.getParameter().getParameter();

        WildcardFilter wildcardFilter = new WildcardFilter();
        wildcardFilter.setOr(or);
        wildcardFilter.setName(name);
        wildcardFilter.setNot(not);
        wildcardFilter.setCode(code);
        wildcardFilter.setParameter(parameter);

        return wildcardFilter;
    }

}
