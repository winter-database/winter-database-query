package io.github.winter.database.query.parser;

import io.github.winter.boot.filter.InFilter;
import io.github.winter.boot.sql.parser.FilterParserImpl;
import io.github.winter.database.query.InQuery;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author changebooks@qq.com
 */
public class QueryFilterParserImpl extends FilterParserImpl {

    @NotEmpty
    @Override
    protected String getInParameterName(@NotNull InFilter filter) {
        if (filter instanceof InQuery) {
            String subQuery = ((InQuery) filter).getSubQuery();
            if (subQuery.isEmpty()) {
                return super.getInParameterName(filter);
            } else {
                return subQuery;
            }
        } else {
            return super.getInParameterName(filter);
        }
    }

}
