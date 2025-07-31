package io.github.winter.database.query;

import jakarta.validation.constraints.NotNull;

/**
 * 解析连表
 *
 * @author changebooks@qq.com
 */
public interface JoinOnParser {
    /**
     * 解析
     *
     * @param logicalOperator 逻辑与或
     * @param leftTable       左表
     * @param leftColumn      左表字段
     * @param rightTable      右表
     * @param rightColumn     右表字段
     * @return table.column = table.column
     */
    String parse(int logicalOperator, String leftTable, String leftColumn, String rightTable, String rightColumn);

    /**
     * 逻辑与或
     *
     * @param logicalOperator 逻辑与或
     * @return AND, OR
     */
    @NotNull
    String getOperator(int logicalOperator);

}
