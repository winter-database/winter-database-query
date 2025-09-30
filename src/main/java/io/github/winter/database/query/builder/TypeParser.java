package io.github.winter.database.query.builder;

import io.github.winter.boot.sql.Preconditions;
import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

/**
 * 类型
 *
 * @author changebooks@qq.com
 */
public final class TypeParser {

    private TypeParser() {
    }

    /**
     * 解析
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 类型
     */
    public static Class<?> parse(@NotNull String tableName, @NotNull String columnName) {
        Preconditions.requireNonEmpty(tableName, "tableName must not be empty");
        Preconditions.requireNonEmpty(columnName, "columnName must not be empty");

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
