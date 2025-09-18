package io.github.winter.database.query;

import io.github.winter.boot.tuple.Pair;
import jakarta.validation.constraints.NotNull;

/**
 * 解析字段名
 *
 * @author changebooks@qq.com
 */
public interface FieldParser {
    /**
     * 解析
     *
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @return [ 字段名 : 字段类型 ]
     */
    @NotNull
    Pair<String, Class<?>> parse(int funcType, String tableName, String columnName);

    /**
     * 解析
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return [ 字段名 : 字段类型 ]
     */
    @NotNull
    Pair<String, Class<?>> parse(String tableName, String columnName);

}
