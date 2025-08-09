package io.github.winter.database.query.parser;

import io.github.winter.boot.filter.ExpressionFilter;
import io.github.winter.boot.filter.InFilter;
import io.github.winter.boot.filter.RangeFilter;
import io.github.winter.boot.sql.parser.FilterParserImpl;
import io.github.winter.database.query.*;

/**
 * @author changebooks@qq.com
 */
public class QueryFilterParserImpl extends FilterParserImpl {
    /**
     * the {@link QueryParser} instance
     */
    private QueryParser queryParser;

    @Override
    public String parseExpression(ExpressionFilter filter) {
        if (filter instanceof QueryExpressionFilter) {
            return parseQueryExpression((QueryExpressionFilter) filter);
        } else {
            return super.parseExpression(filter);
        }
    }

    /**
     * 表达式
     *
     * @param filter the {@link QueryExpressionFilter} instance
     * @return name EQ (SQL), name NE (SQL), name GT (SQL), name LT (SQL)
     */
    protected String parseQueryExpression(QueryExpressionFilter filter) {
        Query subQuery = filter.getSubQuery();
        if (subQuery == null) {
            return super.parseExpression(filter);
        }

        String subQueryName = filter.getSubQueryName();
        String subQuerySql = queryParser.parse(subQuery, subQueryName);

        String pattern = getExpressionPattern(filter);
        String name = getFilterName(filter);
        String value = '(' + subQuerySql + ')';

        return String.format(pattern, name, value);
    }

    @Override
    public String parseIn(InFilter filter) {
        if (filter instanceof QueryInFilter) {
            return parseQueryIn((QueryInFilter) filter);
        } else {
            return super.parseIn(filter);
        }
    }

    /**
     * 在列表中？
     *
     * @param filter the {@link QueryInFilter} instance
     * @return name IN (SQL), name NOT IN (SQL)
     */
    protected String parseQueryIn(QueryInFilter filter) {
        Query subQuery = filter.getSubQuery();
        if (subQuery == null) {
            return super.parseIn(filter);
        }

        String subQueryName = filter.getSubQueryName();
        String subQuerySql = queryParser.parse(subQuery, subQueryName);

        String pattern = getInPattern(filter);
        String name = getFilterName(filter);

        return String.format(pattern, name, subQuerySql);
    }

    @Override
    protected String parseRangeFrom(RangeFilter filter) {
        if (filter instanceof QueryRangeFilter) {
            return parseQueryRangeFrom((QueryRangeFilter) filter);
        } else {
            return super.parseRangeFrom(filter);
        }
    }

    /**
     * 范围开始
     *
     * @param filter the {@link QueryRangeFilter} instance
     * @return name GE (SQL), name GT (SQL)
     */
    protected String parseQueryRangeFrom(QueryRangeFilter filter) {
        Query subQuery = filter.getFromSubQuery();
        if (subQuery == null) {
            return super.parseRangeFrom(filter);
        }

        String subQueryName = filter.getFromSubQueryName();
        String subQuerySql = queryParser.parse(subQuery, subQueryName);

        String pattern = getRangeFromPattern(filter);
        String name = getFilterName(filter);
        String value = '(' + subQuerySql + ')';

        return String.format(pattern, name, value);
    }

    @Override
    protected String parseRangeTo(RangeFilter filter) {
        if (filter instanceof QueryRangeFilter) {
            return parseQueryRangeTo((QueryRangeFilter) filter);
        } else {
            return super.parseRangeTo(filter);
        }
    }

    /**
     * 范围结束
     *
     * @param filter the {@link RangeFilter} instance
     * @return name LE (SQL), name LT (SQL)
     */
    protected String parseQueryRangeTo(QueryRangeFilter filter) {
        Query subQuery = filter.getToSubQuery();
        if (subQuery == null) {
            return super.parseRangeTo(filter);
        }

        String subQueryName = filter.getToSubQueryName();
        String subQuerySql = queryParser.parse(subQuery, subQueryName);

        String pattern = getRangeToPattern(filter);
        String name = getFilterName(filter);
        String value = '(' + subQuerySql + ')';

        return String.format(pattern, name, value);
    }

}
