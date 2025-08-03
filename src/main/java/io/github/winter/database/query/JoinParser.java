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
     * @param type 方式
     * @param name 名称
     * @return JOIN name, LEFT JOIN name, RIGHT JOIN name
     */
    String parse(int type, String name);

}
