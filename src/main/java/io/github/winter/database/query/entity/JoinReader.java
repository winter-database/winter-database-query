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
 * 分组
 *
 * @author changebooks@qq.com
 */
public class JoinReader {

    public List<Entity.Join> read(@NotNull Template template, int queryId) throws SQLException {
        JoinOnReader joinOnReader = new JoinOnReader();

        List<BaseFilter> filters = new ArrayList<>(Collections.singletonList(getRecycleFilter()));
        filters.add(getQueryIdFilter(queryId));

        List<Order> orders = Collections.singletonList(getOrder());

        List<Entity.Join> result = new ArrayList<>();

        List<Map<String, Value>> list = template.selectList("xquery_join", filters, orders, null, null);
        for (Map<String, Value> valueMap : list) {

            Value joinTable = valueMap.get("join_table");
            Value joinType = valueMap.get("join_type");
            Value joinId = valueMap.get("id");

            List<Entity.JoinOn> joinOns = joinOnReader.read(template, queryId, joinId.getInteger());

            Entity.Join record = new Entity.Join();
            record.setJoinTable(joinTable.getString());
            record.setJoinType(joinType.getInteger());
            record.setJoinOns(joinOns);

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
