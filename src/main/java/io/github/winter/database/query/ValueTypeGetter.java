package io.github.winter.database.query;

import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;

import java.util.Map;

/**
 * Value Type Getter
 *
 * @author changebooks@qq.com
 */
public final class ValueTypeGetter {

    private ValueTypeGetter() {
    }

    /**
     * Get Value Type
     *
     * @param tableName  Table Name
     * @param columnName Column Name
     * @return Value Type
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
