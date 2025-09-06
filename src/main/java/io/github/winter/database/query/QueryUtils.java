package io.github.winter.database.query;

import io.github.winter.boot.tuple.Pair;
import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Query Utils
 *
 * @author changebooks@qq.com
 */
public final class QueryUtils {

    private QueryUtils() {
    }

    /**
     * 获取全字段列表
     *
     * @param tableName 表名
     * @return [ [ 字段名 ] : [ 字段名 : 字段类型 ] ]
     */
    @NotNull
    public static Pair<List<String>, Map<String, Class<?>>> getAsterisk(String tableName) {
        TableSchema tableSchema = TableSchemaRegistry.get(tableName);
        if (tableSchema == null) {
            return Pair.of(null, null);
        }

        List<String> columns = new ArrayList<>();
        Map<String, Class<?>> valueTypes = new HashMap<>();

        List<String> columnNames = tableSchema.getColumnNames();
        for (String columnName : columnNames) {
            String joinedColumn = joinName(tableName, columnName);
            if (joinedColumn.isEmpty()) {
                continue;
            }

            Class<?> valueType = getValueType(tableName, columnName);
            if (valueType == null) {
                continue;
            }

            columns.add(joinedColumn);
            valueTypes.put(joinedColumn, valueType);
        }

        return Pair.of(columns, valueTypes);
    }

    /**
     * 连接名称
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 表名 + 字段名
     */
    @NotNull
    public static String joinName(String tableName, String columnName) {
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
     * 获取字段类型
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 字段类型
     */
    public static Class<?> getValueType(String tableName, String columnName) {
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
