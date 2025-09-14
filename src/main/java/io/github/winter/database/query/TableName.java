package io.github.winter.database.query;

import jakarta.validation.constraints.NotEmpty;

/**
 * 表名
 *
 * @author changebooks@qq.com
 */
public final class TableName {

    private TableName() {
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
