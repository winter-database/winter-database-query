package io.github.winter.database.query;

import io.github.winter.boot.filter.InFilter;
import jakarta.validation.constraints.NotNull;

/**
 * 在列表中？
 *
 * @author changebooks@qq.com
 */
public class InQuery extends InFilter {
    /**
     * 参数名
     */
    private String parameterName;

    /**
     * 子查询
     */
    private String subQuery;

    @NotNull
    public String getParameterName() {
        return parameterName != null ? parameterName : "";
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName != null ? parameterName.trim() : "";
    }

    @NotNull
    public String getSubQuery() {
        return subQuery != null ? subQuery : "";
    }

    public void setSubQuery(String subQuery) {
        this.subQuery = subQuery != null ? subQuery.trim() : "";
    }

}
