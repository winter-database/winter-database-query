package io.github.winter.database.query;

/**
 * 解析函数
 *
 * @author changebooks@qq.com
 */
public interface FuncParser {
    /**
     * 解析
     *
     * @param func 函数
     * @param name 名称
     * @return COUNT(1), SUM(name), MAX(name), MIN(name), AVG(name)
     */
    String parse(int func, String name);

}
