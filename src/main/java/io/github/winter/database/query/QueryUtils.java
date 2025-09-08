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

}
