package io.github.winter.database.query;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * 解析连表
 *
 * @author changebooks@qq.com
 */
public interface JoinParser {
    /**
     * 解析列表
     *
     * @param joins [ the {@link Join} instance ]
     * @return LEFT JOIN table2 ON table1.column1 = table2.column2 LEFT JOIN table3 ON table1.column2 = table3.column3
     */
    String parse(List<Join> joins);

    /**
     * 解析
     *
     * @param join the {@link Join} instance
     * @return LEFT JOIN table2 ON table1.column1 = table2.column1 AND table1.column2 = table2.column2
     */
    String parse(Join join);

    /**
     * 解析
     *
     * @param type      连表方式
     * @param tableName 表名
     * @return JOIN name, LEFT JOIN name, RIGHT JOIN name
     */
    @NotEmpty
    String parse(int type, @NotEmpty String tableName);

    /**
     * 解析条件列表
     *
     * @param filters [ the {@link Join.On} instance ]
     * @return table1.column1 = table2.column1 AND table1.column2 = table2.column2
     */
    String parseFilter(List<Join.On> filters);

    /**
     * 解析条件
     *
     * @param filter the {@link Join.On} instance
     * @return table1.column1 = table2.column2
     */
    String parseFilter(Join.On filter);

}
