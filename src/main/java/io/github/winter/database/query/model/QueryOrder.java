package io.github.winter.database.query.model;

import java.io.Serializable;

/**
 * 排序
 *
 * @author changebooks@qq.com
 */
public final class QueryOrder implements Serializable {
    /**
     * 字段
     */
    private QueryColumn column;

    /**
     * 降序？
     */
    private Boolean desc;

}
