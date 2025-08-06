package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;

import java.io.Serializable;
import java.util.List;

/**
 * 分组
 *
 * @author changebooks@qq.com
 */
public class QueryGroup implements Serializable {
    /**
     * 名称列表
     */
    private List<String> names;

    /**
     * 条件列表
     */
    private List<BaseFilter> filters;

}
