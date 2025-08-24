package io.github.winter.database.query;

import io.github.winter.boot.sql.Statement;

/**
 * 解析查询
 *
 * @author changebooks@qq.com
 */
public interface QueryParser {
    /**
     * 解析
     *
     * @param group the {@link Group} instance
     * @return GROUP BY name, name HAVING column = ? AND column = 0
     */

    /**
     *
     *
     * @param query
     * @return
     */
    Statement parse(Query query);

}
