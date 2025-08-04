package io.github.winter.database.query.model;

/**
 * 空
 *
 * @author changebooks@qq.com
 */
public final class QueryFilterNull extends QueryFilter {
    /**
     * 取反？
     */
    private Boolean not;

    public Boolean getNot() {
        return not;
    }

    public void setNot(Boolean not) {
        this.not = not;
    }

}
