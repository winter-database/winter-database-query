package io.github.winter.database.query.refresh;

import org.springframework.context.ApplicationEvent;

/**
 * 刷查询
 *
 * @author changebooks@qq.com
 */
public class RefreshQueryEvent extends ApplicationEvent {
    /**
     * id = 0 ? Refresh All
     */
    private int queryId;

    public RefreshQueryEvent() {
        super("-- Refresh All --");
    }

    public RefreshQueryEvent(int queryId) {
        super(queryId);
        this.queryId = queryId;
    }

    public int getQueryId() {
        return queryId;
    }

}
