package io.github.winter.database.query.model;

import java.io.Serializable;
import java.util.List;

/**
 * 条件
 *
 * @author changebooks@qq.com
 */
public class QueryFilter implements Serializable {
    /**
     * 子条件列表
     */
    private List<QueryFilter> filters;

    /**
     * 字段
     */
    private QueryColumn column;

    /**
     * 逻辑或？
     */
    private Boolean or;

}
