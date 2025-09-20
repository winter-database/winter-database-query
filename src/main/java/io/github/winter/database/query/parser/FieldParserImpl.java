package io.github.winter.database.query.parser;

import io.github.winter.boot.tuple.Pair;
import io.github.winter.database.query.FieldParser;
import io.github.winter.database.query.FuncType;
import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author changebooks@qq.com
 */
public class FieldParserImpl implements FieldParser {

    @Override
    public Pair<String, Class<?>> parse(int funcType, String tableName, String columnName) {
        if (funcType == FuncType.COUNT) {
            return Pair.of("COUNT(*)", Long.class);
        }

        Pair<String, Class<?>> fieldPair = parse(tableName, columnName);

        String fieldName = fieldPair.first;
        if (fieldName.isEmpty()) {
            return Pair.of("", null);
        }

        Class<?> valueType = fieldPair.second;
        if (valueType == null) {
            return Pair.of("", null);
        }

        return switch (funcType) {
            case FuncType.SUM -> Pair.of("SUM(" + fieldName + ")", Long.class);
            case FuncType.MAX -> Pair.of("MAX(" + fieldName + ")", valueType);
            case FuncType.MIN -> Pair.of("MIN(" + fieldName + ")", valueType);
            case FuncType.AVG -> Pair.of("AVG(" + fieldName + ")", BigDecimal.class);
            default -> Pair.of(joinAs(fieldName), valueType);
        };
    }

    @Override
    public Pair<String, Class<?>> parse(String tableName, String columnName) {
        if (tableName == null || tableName.isEmpty()) {
            return Pair.of("", null);
        }

        if (columnName == null || columnName.isEmpty()) {
            return Pair.of("", null);
        }

        TableSchema tableSchema = TableSchemaRegistry.get(tableName);
        if (tableSchema == null) {
            return Pair.of("", null);
        }

        Map<String, Class<?>> valueTypes = tableSchema.getValueTypes();
        if (valueTypes == null) {
            return Pair.of("", null);
        }

        Class<?> valueType = valueTypes.get(columnName);
        if (valueType == null) {
            return Pair.of("", null);
        }

        String fieldName = tableName + '.' + columnName;
        return Pair.of(fieldName, valueType);
    }

    @Override
    public String joinAs(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) {
            return "";
        }

        if (fieldName.indexOf('.') < 0) {
            return fieldName;
        }

        String asName = fieldName.replace('.', '_');
        return fieldName + " AS " + asName;
    }

}
