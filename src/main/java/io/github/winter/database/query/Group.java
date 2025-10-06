package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 分组
 *
 * @author changebooks@qq.com
 */
public final class Group implements Serializable {
    /**
     * 名称列表
     */
    private List<String> names;

    /**
     * 条件列表
     */
    private List<BaseFilter> filters;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = Optional.ofNullable(names)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(Predicate.not(String::isEmpty))
                .distinct()
                .toList();
    }

    public List<BaseFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<BaseFilter> filters) {
        this.filters = Optional.ofNullable(filters)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

}
