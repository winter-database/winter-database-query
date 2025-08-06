package io.github.winter.database.query;

import java.io.Serializable;
import java.util.List;

/**
 * 连表
 *
 * @author changebooks@qq.com
 */
public class QueryJoin implements Serializable {
    /**
     * 名称
     */
    private String name;

    /**
     * 子查询
     */
    private Query subQuery;

    /**
     * 子查询名
     */
    private String subQueryName;

    /**
     * 连表方式
     */
    private int type;

    /**
     * 条件列表
     */
    private List<String> filters;

}
