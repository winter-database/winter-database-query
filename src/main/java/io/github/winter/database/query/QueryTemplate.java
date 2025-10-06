package io.github.winter.database.query;

import io.github.winter.boot.filter.Order;
import io.github.winter.boot.filter.Page;
import io.github.winter.boot.sql.Preconditions;
import io.github.winter.boot.sql.SqlParameter;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.executor.Executor;
import io.github.winter.database.query.parser.QueryParserImpl;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

/**
 * Query Template
 *
 * @author changebooks@qq.com
 */
public class QueryTemplate {
    /**
     * the {@link Executor} instance
     */
    private final Executor executor;

    /**
     * the {@link QueryParser} instance
     */
    private final QueryParser queryParser;

    /**
     * the {@link QueryTemplateLog} instance
     */
    private QueryTemplateLog logWriter;

    public QueryTemplate(Executor executor) {
        Preconditions.requireNonNull(executor, "executor must not be null");

        this.executor = executor;
        this.queryParser = new QueryParserImpl();
    }

    public QueryTemplate(Executor executor, QueryParser queryParser) {
        Preconditions.requireNonNull(executor, "executor must not be null");
        Preconditions.requireNonNull(queryParser, "queryParser must not be null");

        this.executor = executor;
        this.queryParser = queryParser;
    }

    /**
     * SELECT LIST
     *
     * @param queryId    Query Id
     * @param parameters [ Parameter Name : Parameter Value ]
     * @param orders     [ the {@link Order} instance ]
     * @param page       the {@link Page} instance
     * @return [ [ Column Name : Column Value ] ]
     */
    public List<Map<String, Value>> selectList(int queryId, Map<String, Value> parameters, List<Order> orders, Page page) {
        Query query = QueryRegistry.get(queryId);
        Preconditions.requireNonNull(query, "query must not be null, queryId: " + queryId);

        SqlParameter sqlParameter = queryParser.parse(query, orders, page);
        Preconditions.requireNonNull(sqlParameter, "sqlParameter must not be null, queryId: " + queryId);

        replaceParameters(sqlParameter, parameters);

        List<String> columnNames = query.getColumnNames();
        Map<String, Class<?>> valueTypes = query.getValueTypes();

        Executor executor = getExecutor();
        List<Map<String, Value>> result = executor.selectList(sqlParameter, columnNames, valueTypes);

        writeLogSelectList(queryId, parameters, orders, page, result);
        return result;
    }

    /**
     * Replace Parameters
     *
     * @param sqlParameter the {@link SqlParameter} instance
     * @param parameters   [ Parameter Name : Parameter Value ]
     */
    public void replaceParameters(@NotNull SqlParameter sqlParameter, Map<String, Value> parameters) {
        if (parameters == null) {
            return;
        }

        Map<String, Value> originalParameters = sqlParameter.getParameters();
        if (originalParameters == null) {
            sqlParameter.setParameters(parameters);
            return;
        }

        for (Map.Entry<String, Value> entry : parameters.entrySet()) {
            if (entry == null) {
                continue;
            }

            String key = entry.getKey();
            if (key == null) {
                continue;
            }

            Value value = entry.getValue();
            if (value == null) {
                continue;
            }

            originalParameters.put(key, value);
        }

        sqlParameter.setParameters(originalParameters);
    }

    public Executor getExecutor() {
        return executor;
    }

    public QueryParser getQueryParser() {
        return queryParser;
    }

    /**
     * SELECT LIST
     *
     * @param queryId    Query Id
     * @param parameters [ Parameter Name : Parameter Value ]
     * @param orders     [ the {@link Order} instance ]
     * @param page       the {@link Page} instance
     * @param result     [ [ Column Name : Column Value ] ]
     */
    protected void writeLogSelectList(int queryId, Map<String, Value> parameters, List<Order> orders, Page page, List<Map<String, Value>> result) {
        try {
            QueryTemplateLog logWriter = getLogWriter();
            if (logWriter != null) {
                logWriter.selectList(queryId, parameters, orders, page, result);
            }
        } catch (Throwable ignored) {
        }
    }

    @Nullable
    public QueryTemplateLog getLogWriter() {
        return logWriter;
    }

    public void setLogWriter(@Nullable QueryTemplateLog logWriter) {
        this.logWriter = logWriter;
    }

}
