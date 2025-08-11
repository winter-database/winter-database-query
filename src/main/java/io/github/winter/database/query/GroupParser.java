package io.github.winter.database.query;

/**
 * 解析分组
 *
 * @author changebooks@qq.com
 */
public interface GroupParser {
    /**
     * 解析
     *
     * @param group the {@link Group} instance
     * @return GROUP BY name, name HAVING column = 0 AND column = ''
     */
    String parse(Group group);

}
