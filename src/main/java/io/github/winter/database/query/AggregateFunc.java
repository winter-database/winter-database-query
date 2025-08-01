package io.github.winter.database.query;

/**
 * 聚合函数
 *
 * @author changebooks@qq.com
 */
public interface AggregateFunc {
    /**
     * 总行数
     */
    int COUNT = 1;

    /**
     * 总和
     */
    int SUM = 2;

    /**
     * 最大值
     */
    int MAX = 3;

    /**
     * 最小值
     */
    int MIN = 4;

    /**
     * 平均值
     */
    int AVG = 5;

}
