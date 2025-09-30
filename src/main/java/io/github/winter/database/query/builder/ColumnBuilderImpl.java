package io.github.winter.database.query.builder;

import io.github.winter.boot.sql.Preconditions;
import io.github.winter.database.query.Column;
import io.github.winter.database.query.FuncType;
import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author changebooks@qq.com
 */
public class ColumnBuilderImpl implements ColumnBuilder {

    @Override
    public Column build(int funcType, String tableName, String columnName, String rawAsName) {
        String fieldName = joinFunc(funcType, tableName, columnName);
        String asName = NameUtils.asName(fieldName, rawAsName);
        String sqlName = NameUtils.joinAs(fieldName, asName);

        Class<?> valueType = parseType(funcType, tableName, columnName);
        Preconditions.requireNonNull(valueType, "valueType must not be null, sqlName: " + sqlName + ", funcType: " + funcType + ", tableName: " + tableName + ", columnName: " + columnName);

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
    public List<Column> buildAsterisk(List<String> tableNames) {
        if (tableNames == null) {
            return null;
        }

        List<Column> result = new ArrayList<>();

        Set<String> asNameSet = new HashSet<>();
        Set<String> fieldNameSet = new HashSet<>();

        for (String fromTable : tableNames) {
            if (fromTable == null || fromTable.isEmpty()) {
                continue;
            }

            TableSchema tableSchema = TableSchemaRegistry.get(fromTable);
            Preconditions.requireNonNull(tableSchema, "tableSchema must not be null, fromTable: " + fromTable);

            String tableName = tableSchema.getTableName();
            List<String> columnNames = tableSchema.getColumnNames();

            for (String columnName : columnNames) {
                Column column = build(FuncType.NULL, tableName, columnName, null);

                String fieldName = column.getFieldName();
                if (fieldName.isEmpty()) {
                    continue;
                }

                String asName = column.getAsName();
                if (asName.isEmpty()) {
                    if (fieldNameSet.contains(fieldName)) {
                        continue;
                    }
                } else {
                    if (asNameSet.contains(asName)) {
                        continue;
                    }
                }

                result.add(column);

                asNameSet.add(asName);
                fieldNameSet.add(fieldName);
            }
        }

        return result;
    }

    @Override
    public String joinFunc(int funcType, String tableName, String columnName) {
        if (funcType == FuncType.COUNT) {
            return "COUNT(*)";
        }

        Preconditions.requireNonNull(tableName, "tableName must not be null");
        Preconditions.requireNonNull(columnName, "columnName must not be null");
        String fieldName = NameUtils.joinName(tableName, columnName);

        return switch (funcType) {
            case FuncType.SUM -> "SUM(" + fieldName + ")";
            case FuncType.MAX -> "MAX(" + fieldName + ")";
            case FuncType.MIN -> "MIN(" + fieldName + ")";
            case FuncType.AVG -> "AVG(" + fieldName + ")";
            default -> fieldName;
        };
    }

    @Override
    public Class<?> parseType(int funcType, String tableName, String columnName) {
        return switch (funcType) {
            case FuncType.COUNT, FuncType.SUM -> Long.class;
            case FuncType.AVG -> BigDecimal.class;
            default -> parseType(tableName, columnName);
        };
    }

    /**
     * 解析
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 类型
     */
    protected Class<?> parseType(String tableName, String columnName) {
        Preconditions.requireNonNull(tableName, "tableName must not be null");
        Preconditions.requireNonNull(columnName, "columnName must not be null");
        return TypeParser.parse(tableName, columnName);
    }

}
