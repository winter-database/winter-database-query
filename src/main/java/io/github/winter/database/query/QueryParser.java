package io.github.winter.database.query;

/**
 * 解析查询
 *
 * @author changebooks@qq.com
 */
public interface QueryParser {

    String parse(Query query, String name);

}
