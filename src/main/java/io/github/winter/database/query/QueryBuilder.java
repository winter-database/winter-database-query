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
     * @param queryId   查询主键
     * @param fromTable 表名
     * @param asterisk  全字段？
     * @return [ [ 字段名 ] : [ 字段名 : 字段类型 ] ]
     */
    @NotNull
    default Pair<List<String>, Map<String, Class<?>>> selectColumn(int queryId, @NotEmpty String fromTable, boolean asterisk) {
        Pair<List<String>, Map<String, Class<?>>> resultPair = selectColumn(queryId, fromTable);
        if (asterisk) {
            Pair<List<String>, Map<String, Class<?>>> asteriskPair = selectAsterisk(fromTable);

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
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> x));

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
    default Pair<List<String>, Map<String, Class<?>>> selectAsterisk(@NotEmpty String fromTable) {
        TableSchema tableSchema = TableSchemaRegistry.get(fromTable);
        Preconditions.requireNonNull(tableSchema, "tableSchema must not be null, fromTable: " + fromTable);

        List<String> fieldNames = new ArrayList<>();
        Map<String, Class<?>> valueTypes = new HashMap<>();

        String tableName = tableSchema.getTableName();
        List<String> columnNames = tableSchema.getColumnNames();

        for (String columnName : columnNames) {
            String fieldName = joinName(tableName, columnName);
            Class<?> valueType = getClass(tableName, columnName);

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
     * 连接名称
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 表名 + 字段名
     */
    @NotEmpty
    default String joinName(String tableName, String columnName) {
        String fieldName = NameJoiner.join(tableName, columnName);
        Preconditions.requireNonEmpty(fieldName, "fieldName must not be empty, tableName: " + tableName + ", columnName: " + columnName);
        return fieldName;
    }

    /**
     * 连接名称
     *
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @return COUNT(1), SUM(tableName.columnName), MAX(tableName.columnName), MIN(tableName.columnName), AVG(tableName.columnName)
     */
    @NotEmpty
    default String joinName(int funcType, String tableName, String columnName) {
        String fieldName = parseFunc(funcType, tableName, columnName);
        Preconditions.requireNonEmpty(fieldName, "fieldName must not be empty, funcType: " + funcType + ", tableName: " + tableName + ", columnName: " + columnName);
        return fieldName;
    }

    /**
     * 连接名称
     *
     * @param funcType 函数类型
     * @param name     名称
     * @return COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    @NotEmpty
    default String joinName(int funcType, @NotNull String name) {
        String fieldName = parseFunc(funcType, name);
        Preconditions.requireNonEmpty(fieldName, "fieldName must not be empty, funcType: " + funcType + ", name: " + name);
        return fieldName;
    }

    @NotNull
    default Class<?> getClass(String tableName, String columnName) {
        Class<?> valueType = ValueTypeGetter.get(tableName, columnName);
        Preconditions.requireNonNull(valueType, "valueType must not be null, tableName: " + tableName + ", columnName: " + columnName);
        return valueType;
    }

    /**
     * 解析函数
     *
     * @param funcType   函数类型
     * @param tableName  表名
     * @param columnName 字段名
     * @return COUNT(1), SUM(tableName.columnName), MAX(tableName.columnName), MIN(tableName.columnName), AVG(tableName.columnName)
     */
    @NotNull
    String parseFunc(int funcType, String tableName, String columnName);

    /**
     * 解析函数
     *
     * @param funcType 函数类型
     * @param name     名称
     * @return COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    @NotNull
    String parseFunc(int funcType, @NotNull String name);

}
