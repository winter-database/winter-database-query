package io.github.winter.database.query;

import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;

import java.util.Map;

/**
 * 值类型
 *
 * @author changebooks@qq.com
 */
public final class ValueTypeGetter {

    private ValueTypeGetter() {
    }

    /**
     * 获取类型
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 类型
     */
    public static Class<?> get(String tableName, String columnName) {
        if (tableName == null || tableName.isEmpty()) {
            return null;
        }

        if (columnName == null || columnName.isEmpty()) {
            return null;
        }

        TableSchema tableSchema = TableSchemaRegistry.get(tableName);
        if (tableSchema == null) {
            return null;
        }

        Map<String, Class<?>> valueTypes = tableSchema.getValueTypes();
        if (valueTypes == null) {
            return null;
        } else {
            return valueTypes.get(columnName);
        }
    }

}
