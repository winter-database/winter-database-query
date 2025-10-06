package io.github.winter.database.query;

import io.github.winter.boot.sql.Preconditions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 寄存查询
 *
 * @author changebooks@qq.com
 */
public final class QueryRegistry {
    /**
     * [ ID : the {@link Query} instance ]
     */
    private static final Map<Integer, Query> DATA = new ConcurrentHashMap<>();

    private QueryRegistry() {
    }

    /**
     * Get Query
     *
     * @param queryId 查询主键
     * @return the {@link Query} instance
     */
    public static Query get(int queryId) {
        if (queryId > 0) {
            return DATA.get(queryId);
        } else {
            return null;
        }
    }

    /**
     * Put Query
     *
     * @param query the {@link Query} instance
     * @return the {@link Query} instance
     */
    public static Query put(Query query) {
        Preconditions.requireNonNull(query, "query must not be null");

        int queryId = query.getId();
        return DATA.put(queryId, query);
    }

    /**
     * Put Query
     *
     * @param queryId 查询主键
     * @param query   the {@link Query} instance
     * @return the {@link Query} instance
     */
    public static Query put(int queryId, Query query) {
        Preconditions.requireNonNull(query, "query must not be null, queryId: " + queryId);
        return DATA.put(queryId, query);
    }

    /**
     * Remove Query
     *
     * @param queryId 查询主键
     * @return the {@link Query} instance
     */
    public static Query remove(int queryId) {
        return DATA.remove(queryId);
    }

    /**
     * Contains Query ?
     *
     * @param queryId 查询主键
     * @return contains ? true : false
     */
    public static boolean contains(int queryId) {
        if (queryId > 0) {
            return DATA.containsKey(queryId);
        } else {
            return false;
        }
    }

}
