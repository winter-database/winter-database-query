package io.github.winter.database.query;

import io.github.winter.boot.sql.Statement;

import java.util.List;

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
     * @return GROUP BY name, name HAVING column = ? AND column = 0
     */
    Statement parse(Group group);

    /**
     * 解析名称
     *
     * @param names [ name ]
     * @return GROUP BY name, name
     */
    String parse(List<String> names);

    /**
     * 连接前缀
     *
     * @param sql name, name
     * @return GROUP BY name, name
     */
    String prefixed(String sql);

}
