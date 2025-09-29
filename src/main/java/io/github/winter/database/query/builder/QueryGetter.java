package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.Page;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.BooleanCast;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.Optional;

/**
 * 查询属性
 *
 * @author changebooks@qq.com
 */
public final class QueryGetter {

    private QueryGetter() {
    }

    /**
     * 获取主键
     *
     * @param record [ Column Name : Column Value ]
     * @return 主键
     */
    public static int getId(@NotNull Map<String, Value> record) {
        Value value = record.get("id");
        return Optional.ofNullable(value).map(Value::getInteger).orElse(0);
    }

    /**
     * 获取名称
     *
     * @param record [ Column Name : Column Value ]
     * @return 名称
     */
    @NotNull
    public static String getName(@NotNull Map<String, Value> record) {
        Value value = record.get("query_name");
        if (value == null) {
            return "";
        } else {
            String queryName = value.getString();
            return queryName != null ? queryName.trim() : "";
        }
    }

    /**
     * 是否去重
     *
     * @param record [ Column Name : Column Value ]
     * @return 去重？
     */
    public static boolean isDistinct(@NotNull Map<String, Value> record) {
        Value value = record.get("is_distinct");
        Integer distinct = value != null ? value.getInteger() : null;
        return BooleanCast.fromInt(distinct);
    }

    /**
     * 是否全字段
     *
     * @param record [ Column Name : Column Value ]
     * @return 全字段？
     */
    public static boolean isAsterisk(@NotNull Map<String, Value> record) {
        Value value = record.get("is_asterisk");
        Integer asterisk = value != null ? value.getInteger() : null;
        return BooleanCast.fromInt(asterisk);
    }

    /**
     * 是否忽略参数名
     *
     * @param record [ Column Name : Column Value ]
     * @return 忽略参数名？
     */
    public static boolean isParameterName(@NotNull Map<String, Value> record) {
        Value value = record.get("is_parameter_name");
        Integer parameterName = value != null ? value.getInteger() : null;
        return BooleanCast.fromInt(parameterName);
    }

    /**
     * 获取表名
     *
     * @param record [ Column Name : Column Value ]
     * @return 表名
     */
    @NotNull
    public static String getFromTable(@NotNull Map<String, Value> record) {
        Value value = record.get("from_table");
        if (value == null) {
            return "";
        } else {
            String fromTable = value.getString();
            return fromTable != null ? fromTable.trim() : "";
        }
    }

    /**
     * 获取分页
     *
     * @param record [ Column Name : Column Value ]
     * @return the {@link Page} instance
     */
    @NotNull
    public static Page getPage(@NotNull Map<String, Value> record) {
        long pageOffset = getPageOffset(record);
        int pageLimit = getPageLimit(record);

        Page result = new Page();

        result.setOffset(pageOffset);
        result.setLimit(pageLimit);

        return result;
    }

    /**
     * 获取开始行数
     *
     * @param record [ Column Name : Column Value ]
     * @return 开始行数
     */
    public static long getPageOffset(@NotNull Map<String, Value> record) {
        Value value = record.get("page_offset");
        return Optional.ofNullable(value).map(Value::getLong).orElse(0L);
    }

    /**
     * 获取每页行数
     *
     * @param record [ Column Name : Column Value ]
     * @return 每页行数
     */
    public static int getPageLimit(@NotNull Map<String, Value> record) {
        Value value = record.get("page_limit");
        return Optional.ofNullable(value).map(Value::getInteger).orElse(0);
    }

}
