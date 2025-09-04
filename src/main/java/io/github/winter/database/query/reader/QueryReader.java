package io.github.winter.database.query.reader;

import io.github.winter.boot.sql.SqlParameter;
import io.github.winter.database.query.Query;

/**
 * 读取查询
 *
 * @author changebooks@qq.com
 */
public interface QueryReader {
    /**
     * 解析
     *
     * @param query the {@link Query} instance
     * @return the {@link SqlParameter} instance
     */
    Query read(Query query);

}
