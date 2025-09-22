package io.github.winter.database.query.builder;

import io.github.winter.database.query.Column;
import io.github.winter.database.query.ColumnBuilder;
import io.github.winter.database.query.FuncType;
import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author changebooks@qq.com
 */
public class ColumnBuilderImpl implements ColumnBuilder {

    @Override
    public Column build(int funcType, String tableName, String columnName, String aliasName) {
        String fieldName = joinFunc(funcType, tableName, columnName);
        String asName = asName(aliasName, fieldName);
        String sqlName = joinAs(fieldName, asName);
        Class<?> valueType = parseType(funcType, tableName, columnName);

        Column result = new Column();

        result.setSqlName(sqlName);
        result.setFieldName(fieldName);
        result.setAsName(asName);
        result.setFuncType(funcType);
        result.setTableName(tableName);
        result.setColumnName(columnName);
        result.setValueType(valueType);

        return result;
    }

    @Override
    public String joinAs(String fieldName, String asName) {
        if (fieldName == null || fieldName.isEmpty()) {
            return "";
        }

        if (asName == null || asName.isEmpty()) {
            return "";
        } else {
            return fieldName + " AS `" + asName + "`";
        }
    }

    @Override
    public String joinFunc(int funcType, String tableName, String columnName) {
        String fieldName = joinName(tableName, columnName);
        return joinFunc(funcType, fieldName);
    }

    @Override
    public String joinFunc(int funcType, String fieldName) {
        return switch (funcType) {
            case FuncType.COUNT -> "COUNT(*)";
            case FuncType.SUM -> "SUM(" + fieldName + ")";
            case FuncType.MAX -> "MAX(" + fieldName + ")";
            case FuncType.MIN -> "MIN(" + fieldName + ")";
            case FuncType.AVG -> "AVG(" + fieldName + ")";
            default -> fieldName;
        };
    }

    @Override
    public String joinName(String tableName, String columnName) {
        if (columnName == null || columnName.isEmpty()) {
            return "";
        }

        if (tableName == null || tableName.isEmpty()) {
            return columnName;
        } else {
            return tableName + '.' + columnName;
        }
    }

    @Override
    public String asName(String asName, String fieldName) {
        return Stream.of(asName, fieldName)
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isEmpty))
                .findFirst()
                .orElse("");
    }

    @Override
    public Class<?> parseType(int funcType, String tableName, String columnName) {
        return switch (funcType) {
            case FuncType.COUNT, FuncType.SUM -> Long.class;
            case FuncType.AVG -> BigDecimal.class;
            default -> parseType(tableName, columnName);
        };
    }

    @Override
    public Class<?> parseType(String tableName, String columnName) {
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
