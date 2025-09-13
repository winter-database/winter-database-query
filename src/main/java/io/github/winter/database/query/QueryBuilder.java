package io.github.winter.database.query;

import java.util.List;

/**
 * 建造查询
 *
 * @author changebooks@qq.com
 */
public interface QueryBuilder {
    /**
     * 建造全部
     *
     * @return [ the {@link Query} instance ]
     */
    List<Query> buildAll();

    /**
     * 建造
     *
     * @param id 主键
     * @return the {@link Query} instance
     */
    Query build(int id);

}
