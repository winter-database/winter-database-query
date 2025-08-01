package io.github.winter.database.query.constant;

/**
 * 连表类型
 *
 * @author changebooks@qq.com
 */
public interface JoinType {
    /**
     * 内连
     */
    int INNER = 1;

    /**
     * 左外
     */
    int LEFT = 2;

    /**
     * 右外
     */
    int RIGHT = 3;

}
