package io.github.winter.database.query.reader;

import io.github.winter.boot.filter.*;
import io.github.winter.boot.tuple.Value;

/**
 * 读数据库
 *
 * @author changebooks@qq.com
 */
public final class ReaderUtils {
    /**
     * 1-True
     * 0-False
     */
    public static final int TRUE = 1;

    private ReaderUtils() {
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
     * 获取条件
     *
     * @param name  名称
     * @param value 值
     * @return the {@link Filter} instance
     */
    public static Filter newFilter(String name, int value) {
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
     * 获取排序
     *
     * @return the {@link Order} instance
     */
    public static Order newOrder() {
        Order order = new Order();
        order.setName("show_priority");
        return order;
    }

}
