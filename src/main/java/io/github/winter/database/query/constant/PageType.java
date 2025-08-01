package io.github.winter.database.query.constant;

/**
 * 分页方式
 *
 * @author changebooks@qq.com
 */
public interface PageType {
    /**
     * LIMIT offset, limit
     */
    int OFFSET = 1;

    /**
     * FROM id LIMIT limit
     */
    int ID_RANGE = 2;

}
