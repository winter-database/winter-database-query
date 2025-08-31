package io.github.winter.database.query.dto;

import io.github.winter.boot.filter.*;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.template.Template;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Data Transfer Object
 *
 * @author changebooks@qq.com
 */
public final class DtoUtils {
    /**
     * 1-True
     * 0-False
     */
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    private DtoUtils() {
    }

    /**
     * 类型转换
     *
     * @param value 整数
     * @return 布尔
     */
    public static Boolean toBoolean(Integer value) {
        if (value != null) {
            return value == TRUE;
        } else {
            return null;
        }
    }

    /**
     * 类型转换
     *
     * @param value 布尔
     * @return 整数
     */
    public static int toInt(boolean value) {
        return value ? TRUE : FALSE;
    }

    /**
     * SELECT LIST
     *
     * @param template  the {@link Template} instance
     * @param tableName 表名
     * @param queryId   查询主键
     * @param filterId  条件主键
     * @return [ [ Column Name : Column Value ] ]
     */
    public static List<Map<String, Value>> selectList(@NotNull Template template,
                                                      @NotNull String tableName,
                                                      int queryId,
                                                      int filterId) {
        List<BaseFilter> filters = new ArrayList<>();

        filters.add(newFilter("internal_recycle", 0));
        filters.add(newFilter("query_id", queryId));
        filters.add(newFilter("filter_id", filterId));

        return selectList(template, tableName, filters);
    }

    /**
     * SELECT LIST
     *
     * @param template  the {@link Template} instance
     * @param tableName 表名
     * @param queryId   查询主键
     * @return [ [ Column Name : Column Value ] ]
     */
    public static List<Map<String, Value>> selectList(@NotNull Template template,
                                                      @NotNull String tableName,
                                                      int queryId) {
        List<BaseFilter> filters = new ArrayList<>();

        filters.add(newFilter("internal_recycle", 0));
        filters.add(newFilter("query_id", queryId));

        return selectList(template, tableName, filters);
    }

    /**
     * SELECT LIST
     *
     * @param template  the {@link Template} instance
     * @param tableName 表名
     * @param filters   [ the {@link BaseFilter} instance ]
     * @return [ [ Column Name : Column Value ] ]
     */
    public static List<Map<String, Value>> selectList(@NotNull Template template,
                                                      @NotNull String tableName,
                                                      List<BaseFilter> filters) {
        List<Order> orders = newOrders();
        return template.selectList(tableName, filters, orders, null, null);
    }

    /**
     * SELECT ONE
     *
     * @param template  the {@link Template} instance
     * @param tableName 表名
     * @param id        主键
     * @return [ Column Name : Column Value ]
     */
    public static Map<String, Value> selectOne(@NotNull Template template,
                                               @NotNull String tableName,
                                               int id) {
        List<BaseFilter> filters = new ArrayList<>();

        filters.add(newFilter("internal_recycle", 0));
        filters.add(newFilter("id", id));

        return template.selectOne(tableName, filters, null, null);
    }

    /**
     * 条件
     *
     * @param name  名称
     * @param value 值
     * @return the {@link BaseFilter} instance
     */
    public static BaseFilter newFilter(String name, int value) {
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
     * 排序列表
     *
     * @return [ the {@link Order} instance ]
     */
    public static List<Order> newOrders() {
        return Collections.singletonList(newOrder());
    }

    /**
     * 排序
     *
     * @return the {@link Order} instance
     */
    public static Order newOrder() {
        Order order = new Order();
        order.setName("show_priority");
        return order;
    }

}
