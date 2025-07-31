package io.github.winter.database.query;

/**
 * 解析连表
 *
 * @author changebooks@qq.com
 */
public interface JoinParser {
    /**
     * 解析
     *
     * @param joinTable 表名
     * @param joinEnum  连表方式
     * @return JOIN table
     */
    String parse(String joinTable, int joinEnum);

}
