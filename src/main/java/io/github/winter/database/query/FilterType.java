package io.github.winter.database.query;

/**
 * 条件类型
 *
 * @author changebooks@qq.com
 */
public interface FilterType {
    /**
     * 表达式
     */
    int EXPRESSION = 1;

    /**
     * 在列表中？
     */
    int IN = 2;

    /**
     * 空？
     */
    int NULL = 3;

    /**
     * 范围
     */
    int RANGE = 4;

    /**
     * 模糊匹配
     */
    int WILDCARD = 5;

}
