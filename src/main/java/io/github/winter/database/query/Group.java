package io.github.winter.database.query;

import io.github.winter.boot.filter.BaseFilter;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.*;

/**
 * 分组
 *
 * @author changebooks@qq.com
 */
public class Group implements Serializable {
    /**
     * 名称列表
     */
    private List<String> names;

    /**
     * 条件列表
     */
    private List<BaseFilter> filters;

    @NotNull
    public List<String> getNames() {
        return names != null ? names : new ArrayList<>();
    }

    public void setNames(List<String> names) {
        this.names = Optional.ofNullable(names)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .distinct()
                .toList();
    }

    public List<BaseFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<BaseFilter> filters) {
        this.filters = filters;
    }

}
