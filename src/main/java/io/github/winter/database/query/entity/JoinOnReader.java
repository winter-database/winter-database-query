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
public class JoinOnReader {

    public List<Entity.JoinOn> read(@NotNull Template template, int queryId, int joinId) throws SQLException {
        List<BaseFilter> filters = new ArrayList<>(Collections.singletonList(getRecycleFilter()));
        filters.add(getQueryIdFilter(queryId));
        filters.add(getJoinIdFilter(joinId));

        List<Order> orders = Collections.singletonList(getOrder());

        List<Entity.JoinOn> result = new ArrayList<>();

        List<Map<String, Value>> list = template.selectList("xquery_join_on", filters, orders, null, null);
        for (Map<String, Value> valueMap : list) {

            Value leftTable = valueMap.get("left_table");
            Value leftColumn = valueMap.get("left_column");
            Value rightTable = valueMap.get("right_table");
            Value rightColumn = valueMap.get("right_column");

            Entity.JoinOn record = new Entity.JoinOn();
            record.setLeftTable(leftTable.getString());
            record.setLeftColumn(leftColumn.getString());
            record.setRightTable(rightTable.getString());
            record.setRightColumn(rightColumn.getString());

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

    public ExpressionFilter getJoinIdFilter(int joinId) {
        String columnName = "join_id";

        Parameter parameter = new Parameter();
        parameter.setName(columnName);
        parameter.setValue(new Value(joinId));

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
