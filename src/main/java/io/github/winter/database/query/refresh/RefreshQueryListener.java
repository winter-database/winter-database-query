package io.github.winter.database.query.refresh;

import io.github.winter.boot.sql.Preconditions;
import io.github.winter.database.query.Query;
import io.github.winter.database.query.QueryRegistry;
import io.github.winter.database.query.builder.QueryBuilder;
import io.github.winter.database.query.builder.QueryBuilderImpl;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 刷查询
 *
 * @author changebooks@qq.com
 */
public class RefreshQueryListener implements ApplicationListener<RefreshQueryEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshQueryListener.class);

    /**
     * the {@link ApplicationEventPublisher} instance
     */
    private final ApplicationEventPublisher publisher;

    /**
     * the {@link QueryBuilder} instance
     */
    private final QueryBuilder builder;

    public RefreshQueryListener(ApplicationEventPublisher publisher, JdbcTemplate jdbcTemplate) {
        Preconditions.requireNonNull(publisher, "publisher must not be null");
        Preconditions.requireNonNull(jdbcTemplate, "jdbcTemplate must not be null");

        this.publisher = publisher;
        this.builder = new QueryBuilderImpl(jdbcTemplate);
    }

    @Override
    public void onApplicationEvent(RefreshQueryEvent event) {
        int queryId = event.getQueryId();
        if (queryId > 0) {
            refresh(queryId);
        } else {
            refreshAll();
        }
    }

    /**
     * Publish Refresh All
     */
    public void publishRefreshAll() {
        Thread.ofVirtual().start(this::doPublishRefreshAll);
    }

    /**
     * Publish Refresh
     *
     * @param queryId 查询主键
     */
    public void publishRefresh(int queryId) {
        Thread.ofVirtual().start(() -> doPublishRefresh(queryId));
    }

    /**
     * Publish Refresh All
     */
    protected void doPublishRefreshAll() {
        try {
            ApplicationEventPublisher publisher = getPublisher();
            publisher.publishEvent(new RefreshQueryEvent());
        } catch (Throwable ex) {
            LOGGER.error("doPublishRefreshAll failed, throwable: ", ex);
        }
    }

    /**
     * Publish Refresh
     *
     * @param queryId 查询主键
     */
    protected void doPublishRefresh(int queryId) {
        try {
            ApplicationEventPublisher publisher = getPublisher();
            publisher.publishEvent(new RefreshQueryEvent(queryId));
        } catch (Throwable ex) {
            LOGGER.error("doPublishRefresh failed, queryId: {}, throwable: ", queryId, ex);
        }
    }

    /**
     * Refresh All
     */
    public void refreshAll() {
        List<Integer> ids = getIds();
        if (ids == null) {
            return;
        }

        for (Integer id : ids) {
            if (id == null) {
                continue;
            }

            if (id > 0) {
                refresh(id);
            }
        }
    }

    /**
     * Refresh
     *
     * @param queryId 查询主键
     */
    public void refresh(int queryId) {
        try {
            Query query = builder.build(queryId);
            Preconditions.requireNonNull(query, "query must not be null, queryId: " + queryId);

            int id = query.getId();
            QueryRegistry.put(id, query);

            LOGGER.info("refresh trace, queryId: {}", queryId);
        } catch (Throwable ex) {
            LOGGER.error("refresh failed, queryId: {}, throwable: ", queryId, ex);
        }
    }

    /**
     * Get Id
     *
     * @return [ Id ]
     */
    @Nullable
    public List<Integer> getIds() {
        try {
            return builder.selectIds();
        } catch (Throwable ex) {
            LOGGER.error("getIds failed, throwable: ", ex);
            return null;
        }
    }

    public ApplicationEventPublisher getPublisher() {
        return publisher;
    }

    public QueryBuilder getBuilder() {
        return builder;
    }

}
