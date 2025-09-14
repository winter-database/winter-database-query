package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.filter.Order;
import io.github.winter.boot.sql.Preconditions;
import io.github.winter.boot.tuple.Pair;
import io.github.winter.database.table.TableSchema;
import io.github.winter.database.template.TableSchemaRegistry;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 创建查询
 *
 * @author changebooks@qq.com
 */
public interface QueryBuilder {
    /**
     * 全部主键
     *
     * @return 主键列表
     */
    @NotNull
    List<Integer> selectIds();

    /**
     * 创建
     *
     * @param id 主键
     * @return the {@link Query} instance
     */
    Query build(int id);

    /**
     * 字段
     *
     * @param queryId    查询主键
     * @param fromTable  表名
     * @param isAsterisk 全字段？
     * @return [ [ 字段名 ] : [ 字段名 : 字段类型 ] ]
     */
    @NotNull
    default Pair<List<String>, Map<String, Class<?>>> selectColumn(int queryId, @NotEmpty String fromTable, boolean isAsterisk) {
        Pair<List<String>, Map<String, Class<?>>> resultPair = selectColumn(queryId, fromTable);
        if (isAsterisk) {
            Pair<List<String>, Map<String, Class<?>>> asteriskPair = asterisk(fromTable);

            List<String> columns = Stream.concat
                            (
                                    Optional.ofNullable(resultPair.first).orElse(Collections.emptyList()).stream(),
                                    Optional.ofNullable(asteriskPair.first).orElse(Collections.emptyList()).stream()
                            )
                    .toList();

            Map<String, Class<?>> valueTypes = Stream.of
                            (
                                    Optional.ofNullable(resultPair.second).orElse(Collections.emptyMap()),
                                    Optional.ofNullable(asteriskPair.second).orElse(Collections.emptyMap())
                            )
                    .flatMap(x -> x.entrySet().stream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (result, asterisk) -> result));

            return Pair.of(columns, valueTypes);
        } else {
            return resultPair;
        }
    }

    /**
     * 全字段
     *
     * @param fromTable 表名
     * @return [ [ 字段名 ] : [ 字段名 : 字段类型 ] ]
     */
    @NotNull
    default Pair<List<String>, Map<String, Class<?>>> asterisk(@NotEmpty String fromTable) {
        TableSchema tableSchema = TableSchemaRegistry.get(fromTable);
        Preconditions.requireNonNull(tableSchema, "tableSchema must not be null, fromTable: " + fromTable);

        List<String> fieldNames = new ArrayList<>();
        Map<String, Class<?>> valueTypes = new HashMap<>();

        String tableName = tableSchema.getTableName();
        List<String> columnNames = tableSchema.getColumnNames();

        for (String columnName : columnNames) {
            String fieldName = fieldName(tableName, columnName);
            Class<?> valueType = valueType(tableName, columnName);

            fieldNames.add(fieldName);
            valueTypes.put(fieldName, valueType);
        }

        return Pair.of(fieldNames, valueTypes);
    }

    /**
     * 字段
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ [ 字段名 ] : [ 字段名 : 字段类型 ] ]
     */
    @NotNull
    Pair<List<String>, Map<String, Class<?>>> selectColumn(int queryId, @NotEmpty String fromTable);

    /**
     * 连表
     *
     * @param queryId 查询主键
     * @return [ the {@link Join} instance ]
     */
    List<Join> selectJoin(int queryId);

    /**
     * 连表条件
     *
     * @param queryId 查询主键
     * @param joinId  连表主键
     * @return [ the {@link Join.On} instance ]
     */
    List<Join.On> selectJoinOn(int queryId, int joinId);

    /**
     * 分组
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return the {@link Group} instance
     */
    Group selectGroup(int queryId, @NotEmpty String fromTable);

    /**
     * 排序
     *
     * @param queryId   查询主键
     * @param fromTable 表名
     * @return [ the {@link Order} instance ]
     */
    List<Order> selectOrder(int queryId, @NotEmpty String fromTable);

    /**
     * 条件
     *
     * @param isHaving  分组条件？
     * @param queryId   查询主键
     * @param parentId  父条件主键
     * @param fromTable 表名
     * @return [ the {@link BaseFilter} instance ]
     */
    List<BaseFilter> selectFilter(int isHaving, int queryId, int parentId, @NotEmpty String fromTable);

    /**
     * 字段名
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 表名 + 字段名
     */
    @NotEmpty
    default String fieldName(String tableName, String columnName) {
        String fieldName = NameJoiner.join(tableName, columnName);
        Preconditions.requireNonEmpty(fieldName, "fieldName must not be empty, tableName: " + tableName + ", columnName: " + columnName);

        return fieldName;
    }

    /**
     * 字段名
     *
     * @param func       函数
     * @param tableName  表名
     * @param columnName 字段名
     * @return COUNT(1), SUM(tableName.columnName), MAX(tableName.columnName), MIN(tableName.columnName), AVG(tableName.columnName)
     */
    @NotEmpty
    default String fieldName(int func, String tableName, String columnName) {
        String fieldName = parseFunc(func, tableName, columnName);
        Preconditions.requireNonEmpty(fieldName, "fieldName must not be empty, func: " + func + ", tableName: " + tableName + ", columnName: " + columnName);

        return fieldName;
    }

    /**
     * 字段名
     *
     * @param func 函数
     * @param name 名称
     * @return COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    @NotEmpty
    default String fieldName(int func, @NotNull String name) {
        String fieldName = parseFunc(func, name);
        Preconditions.requireNonEmpty(fieldName, "fieldName must not be empty, func: " + func + ", name: " + name);

        return fieldName;
    }

    /**
     * 值类型
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 类型
     */
    @NotNull
    default Class<?> valueType(String tableName, String columnName) {
        Class<?> valueType = ValueTypeGetter.get(tableName, columnName);
        Preconditions.requireNonNull(valueType, "valueType must not be null, tableName: " + tableName + ", columnName: " + columnName);

        return valueType;
    }

    /**
     * 解析
     *
     * @param func       函数
     * @param tableName  表名
     * @param columnName 字段名
     * @return COUNT(1), SUM(tableName.columnName), MAX(tableName.columnName), MIN(tableName.columnName), AVG(tableName.columnName)
     */
    @NotNull
    String parseFunc(int func, String tableName, String columnName);

    /**
     * 解析
     *
     * @param func 函数
     * @param name 名称
     * @return COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    @NotNull
    String parseFunc(int func, @NotNull String name);

}
