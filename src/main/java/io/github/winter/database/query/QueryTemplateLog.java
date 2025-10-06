package io.github.winter.database.query;

import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.tuple.Value;

import java.util.List;
import java.util.Map;

/**
 * Query Template Log
 *
 * @author changebooks@qq.com
 */
public interface QueryTemplateLog {
    /**
     * SELECT LIST
     *
     * @param queryId    Query Id
     * @param parameters [ Parameter Name : Parameter Value ]
     * @param orders     [ the {@link Order} instance ]
     * @param page       the {@link Page} instance
     * @param result     [ [ Column Name : Column Value ] ]
     */
    void selectList(int queryId, Map<String, Value> parameters, List<Order> orders, Page page, List<Map<String, Value>> result);

}
