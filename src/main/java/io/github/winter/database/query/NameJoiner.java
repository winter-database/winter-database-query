package io.github.winter.database.query;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * 连接名称
 *
 * @author changebooks@qq.com
 */
public final class NameJoiner {

    private NameJoiner() {
    }

    /**
     * 连接
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 表名 + 字段名
     */
    @NotNull
    public static String join(String tableName, String columnName) {
        if (columnName == null || columnName.isEmpty()) {
            return "";
        }

        if (tableName == null || tableName.isEmpty()) {
            return columnName;
        } else {
            return tableName + '.' + columnName;
        }
    }

    /**
     * 选择表名
     *
     * @param tableName 表名
     * @param fromTable 默认表名
     * @return Optional.of(tableName).filter(Predicate.not String.isEmpty).orElse(fromTable);
     */
    @NotEmpty
    public static String defaultName(String tableName, @NotEmpty String fromTable) {
        if (tableName == null || tableName.isEmpty()) {
            return fromTable;
        } else {
            return tableName;
        }
    }

}
