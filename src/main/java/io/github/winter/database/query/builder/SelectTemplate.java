package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.*;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.executor.Executor;
import io.github.winter.database.template.Template;
import jakarta.validation.constraints.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * Select Template
 *
 * @author changebooks@qq.com
 */
public class SelectTemplate {
    /**
     * the {@link Template} instance
     */
    private final Template template;

    public SelectTemplate(JdbcTemplate jdbcTemplate) {
        this.template = new Template(new Executor(jdbcTemplate));
    }

    /**
     * SELECT LIST
     *
     * @param tableName FROM table
     * @param filters   [ Column Name : Column Value ]
     * @return [ [ Column Name : Column Value ] ]
     */
    public List<Map<String, Value>> selectList(@NotNull String tableName, Map<String, Integer> filters) {
        Template template = getTemplate();
        return template.selectList(tableName, buildFilters(filters), defaultOrders(), null, null);
    }

    /**
     * SELECT ONE
     *
     * @param tableName FROM table
     * @param filters   [ Column Name : Column Value ]
     * @return [ Column Name : Column Value ]
     */
    public Map<String, Value> selectOne(@NotNull String tableName, Map<String, Integer> filters) {
        Template template = getTemplate();
        return template.selectOne(tableName, buildFilters(filters), null, null);
    }

    /**
     * Build Filter List
     *
     * @param filters [ Column Name : Column Value ]
     * @return [ the {@link BaseFilter} instance ]
     */
    @NotNull
    public List<BaseFilter> buildFilters(Map<String, Integer> filters) {
        Map<String, Integer> filterMap = defaultFilters();
        if (filters != null) {
            filterMap.putAll(filters);
        }

        return filterMap
                .entrySet()
                .stream()
                .filter(Objects::nonNull)
                .map(entry -> buildFilter(entry.getKey(), entry.getValue()))
                .toList();
    }

    /**
     * Build Filter
     *
     * @param name  Column Name
     * @param value Column Value
     * @return the {@link BaseFilter} instance
     */
    @NotNull
    public BaseFilter buildFilter(String name, Integer value) {
        ExpressionFilter filter = new ExpressionFilter();

        Parameter parameter = new Parameter();
        parameter.setName(name);
        parameter.setValue(new Value(value));

        filter.setCode(ExpressionCode.EQ);
        filter.setName(name);
        filter.setParameter(parameter);

        return filter;
    }

    /**
     * Default Filter Map
     *
     * @return [ Column Name : Column Value ]
     */
    @NotNull
    public Map<String, Integer> defaultFilters() {
        Map<String, Integer> filters = new HashMap<>();
        filters.put("internal_recycle", 0);
        return filters;
    }

    /**
     * Default Order List
     *
     * @return [ the {@link Order} instance ]
     */
    @NotNull
    public List<Order> defaultOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(defaultOrder());
        return orders;
    }

    /**
     * Default Order
     *
     * @return the {@link Order} instance
     */
    @NotNull
    public Order defaultOrder() {
        Order order = new Order();
        order.setName("show_priority");
        return order;
    }

    public Template getTemplate() {
        return template;
    }

}
