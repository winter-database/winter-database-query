package io.github.winter.database.query;

import io.github.winter.boot.tuple.Pair;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.*;

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
            String column = joinName(tableName, columnName);
            if (column.isEmpty()) {
                continue;
            }

            Class<?> valueType = getValueType(tableName, columnName);
            if (valueType == null) {
                continue;
            }

            columns.add(column);
            valueTypes.put(column, valueType);
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
     * 获取值
     *
     * @param valueType       类型
     * @param valueString     字符串
     * @param valueInteger    整数
     * @param valueLong       长整数
     * @param valueBigDecimal 小数
     * @param valueDate       日期时间
     * @return the {@link Value} instance
     */
    public static Value getValue(Class<?> valueType,
                                 String valueString, Integer valueInteger, Long valueLong, BigDecimal valueBigDecimal, Date valueDate) {
        if (valueType == null) {
            return null;
        }

        if (String.class == valueType) {
            return new Value(valueString);
        }

        if (Integer.class == valueType) {
            return new Value(valueInteger);
        }

        if (Long.class == valueType) {
            return new Value(valueLong);
        }

        if (BigDecimal.class == valueType) {
            return new Value(valueBigDecimal);
        }

        if (Date.class == valueType) {
            return new Value(valueDate);
        }

        return null;
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
