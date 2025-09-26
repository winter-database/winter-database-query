package io.github.winter.database.query;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 连表
 *
 * @author changebooks@qq.com
 */
public final class Join implements Serializable {
    /**
     * 连表方式
     */
    private int type;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 条件列表
     */
    private List<On> filters;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @NotNull
    public String getTableName() {
        return tableName != null ? tableName : "";
    }

    public void setTableName(String tableName) {
        this.tableName = tableName != null ? tableName.trim() : "";
    }

    public List<On> getFilters() {
        return filters;
    }

    public void setFilters(List<On> filters) {
        this.filters = Optional.ofNullable(filters)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    /**
     * 连表条件
     */
    public static class On implements Serializable {
        /**
         * 左表名 + 左表字段名
         */
        private String leftName;

        /**
         * 右表名 + 右表字段名
         */
        private String rightName;

        @NotNull
        public String getLeftName() {
            return leftName != null ? leftName : "";
        }

        public void setLeftName(String leftName) {
            this.leftName = leftName != null ? leftName.trim() : "";
        }

        @NotNull
        public String getRightName() {
            return rightName != null ? rightName : "";
        }

        public void setRightName(String rightName) {
            this.rightName = rightName != null ? rightName.trim() : "";
        }

    }

}
