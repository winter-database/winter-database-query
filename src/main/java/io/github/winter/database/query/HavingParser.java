package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import io.github.winter.boot.sql.Statement;

import java.util.List;

/**
 * 解析条件
 *
 * @author changebooks@qq.com
 */
public interface HavingParser {
    /**
     * 解析列表
     *
     * @param filters [ the {@link BaseFilter} instance ]
     * @return the {@link Statement} instance
     */
    Statement parse(List<BaseFilter> filters);

    /**
     * 解析
     *
     * @param filter the {@link BaseFilter} instance
     * @return the {@link Statement} instance
     */
    Statement parse(BaseFilter filter);

    /**
     * 连接前缀
     *
     * @param statement the {@link Statement} instance
     * @return the {@link Statement} instance
     */
    Statement prefixed(Statement statement);

    /**
     * 连接前缀
     *
     * @param sql column = ? AND column = 0
     * @return HAVING column = ? AND column = 0
     */
    String prefixed(String sql);

}
