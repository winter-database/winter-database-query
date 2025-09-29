package io.github.winter.database.query.builder;

import io.github.winter.database.query.Join;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建表名
 *
 * @author changebooks@qq.com
 */
public final class TableNameBuilder {

    private TableNameBuilder() {
    }

    /**
     * 创建
     *
     * @param fromTable 表名
     * @param joins     [ the {@link Join} instance ]
     * @return [ 表名 ]
     */
    @NotEmpty
    public static List<String> build(@NotEmpty String fromTable, List<Join> joins) {
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

}
