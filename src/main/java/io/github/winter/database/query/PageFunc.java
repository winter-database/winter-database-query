package io.github.winter.database.query;

/**
 * 分页
 *
 * @author changebooks@qq.com
 */
public interface PageFunc {
    /**
     * LIMIT offset, limit
     */
    int OFFSET = 1;

    /**
     * FROM id LIMIT limit
     */
    int PRIMARY_KEY = 2;

}
