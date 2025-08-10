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
     * id SCROLL LIMIT limit
     */
    int SCROLL = 2;

}
