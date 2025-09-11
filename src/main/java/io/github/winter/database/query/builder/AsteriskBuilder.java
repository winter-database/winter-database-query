package io.github.winter.database.query.builder;

import io.github.winter.boot.tuple.Pair;
import io.github.winter.database.query.NameJoiner;
import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Asterisk Builder
 *
 * @author changebooks@qq.com
 */
public final class AsteriskBuilder {

    private AsteriskBuilder() {
    }

    /**
     * Build
     *
     * @param fromTable Table Name
     * @return [ [ Field Name ] : [ Field Name : Value Type ] ]
     */
    @NotNull
    public static Pair<List<String>, Map<String, Class<?>>> build(String fromTable) {
        TableSchema tableSchema = TableSchemaRegistry.get(fromTable);
        if (tableSchema == null) {
            return Pair.of(null, null);
        }

        List<String> fieldNames = new ArrayList<>();
        Map<String, Class<?>> valueTypes = new HashMap<>();

        String tableName = tableSchema.getTableName();
        List<String> columnNames = tableSchema.getColumnNames();

        for (String columnName : columnNames) {
            String fieldName = NameJoiner.join(tableName, columnName);
            if (fieldName.isEmpty()) {
                continue;
            }

            Class<?> valueType = ValueTypeGetter.get(tableName, columnName);
            if (valueType == null) {
                continue;
            }

            fieldNames.add(fieldName);
            valueTypes.put(fieldName, valueType);
        }

        return Pair.of(fieldNames, valueTypes);
    }

}
