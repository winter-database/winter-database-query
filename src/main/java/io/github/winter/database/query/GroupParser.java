package io.github.winter.database.query;

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
     * @param list 名称列表
     * @return GROUP BY name, name
     */
    String parse(List<String> list);

    /**
     * 连接前缀
     *
     * @param sql name, name
     * @return GROUP BY name, name
     */
    String prefixed(String sql);

}
