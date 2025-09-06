package io.github.winter.database.query;

/**
 * 解析函数
 *
 * @author changebooks@qq.com
 */
public final class FuncParser {
    /**
     * 总行数
     */
    public static final String COUNT = "COUNT(*)";

    private FuncParser() {
    }

    /**
     * 解析
     *
     * @param func       函数
     * @param tableName  表名
     * @param columnName 字段名
     * @return COUNT(1), SUM(tableName.columnName), MAX(tableName.columnName), MIN(tableName.columnName), AVG(tableName.columnName)
     */
    public static String parse(int func, String tableName, String columnName) {
        String name = QueryUtils.joinName(tableName, columnName);
        return parse(func, name);
    }

    /**
     * 解析
     *
     * @param func 函数
     * @param name 名称
     * @return COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    public static String parse(int func, String name) {
        return switch (func) {
            case FuncType.COUNT -> COUNT;
            case FuncType.SUM -> "SUM(" + name + ")";
            case FuncType.MAX -> "MAX(" + name + ")";
            case FuncType.MIN -> "MIN(" + name + ")";
            case FuncType.AVG -> "AVG(" + name + ")";
            default -> name;
        };
    }

}
