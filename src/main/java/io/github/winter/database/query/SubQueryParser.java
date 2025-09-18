package io.github.winter.database.query;

import jakarta.validation.constraints.NotNull;

/**
 * 解析子查询
 *
 * @author changebooks@qq.com
 */
public interface SubQueryParser {
    /**
     * 解析
     *
     * @param subQuery  子查询
     * @param tableName 表名
     * @return (SELECT column FROM table) AS name
     */
    @NotNull
    String parse(String subQuery, String tableName);

}
