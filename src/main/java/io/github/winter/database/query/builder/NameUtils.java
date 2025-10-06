package io.github.winter.database.query.builder;

import io.github.winter.boot.sql.Preconditions;
import io.github.winter.database.query.Join;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 表名和字段名
 *
 * @author changebooks@qq.com
 */
public final class NameUtils {

    private NameUtils() {
    }

    /**
     * 全部表名
     *
     * @param fromTable 表名
     * @param joins     [ the {@link Join} instance ]
     * @return [ 表名 ]
     */
    @NotEmpty
    public static List<String> getTableName(@NotNull String fromTable, List<Join> joins) {
        Preconditions.requireNonEmpty(fromTable, "fromTable must not be empty");

        List<String> result = new ArrayList<>();
        result.add(fromTable);

        if (joins == null) {
            return result;
        }

        for (Join join : joins) {
            if (join == null) {
                continue;
            }

            String tableName = join.getTableName();
            if (tableName.isEmpty()) {
                continue;
            }

            if (result.contains(tableName)) {
                continue;
            }

            result.add(tableName);
        }

        return result;
    }

    /**
     * 连接表名
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 表名 + 字段名
     */
    @NotEmpty
    public static String joinName(@NotNull String tableName, @NotNull String columnName) {
        Preconditions.requireNonEmpty(tableName, "tableName must not be empty");
        Preconditions.requireNonEmpty(columnName, "columnName must not be empty");
        return tableName + '.' + columnName;
    }

    /**
     * 连接别名
     *
     * @param fieldName 函数 + 表名 + 字段名
     * @param asName    别名
     * @return 函数 + 表名 + 字段名 + 别名
     */
    @NotEmpty
    public static String joinAs(@NotEmpty String fieldName, String asName) {
        if (asName == null || asName.isEmpty()) {
            return fieldName;
        } else {
            return fieldName + " AS `" + asName + "`";
        }
    }

    /**
     * 解析别名
     *
     * @param fieldName 函数 + 表名 + 字段名
     * @param asName    自定义的别名
     * @return 自定义的别名或解析的别名
     */
    @NotEmpty
    public static String asName(@NotEmpty String fieldName, String asName) {
        if (asName == null || asName.isEmpty()) {
            return fieldName;
        } else {
            return asName;
        }
    }

}
