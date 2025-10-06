package io.github.winter.database.query.builder;

import io.github.winter.boot.filter.Parameter;
import io.github.winter.boot.tuple.Value;
import io.github.winter.database.query.entity.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数
 *
 * @author changebooks@qq.com
 */
public final class ParameterUtils {

    private ParameterUtils() {
    }

    /**
     * 参数
     *
     * @param parameterName 参数名
     * @param filter        条件
     * @param valueType     参数类型
     * @return the {@link Parameter} instance
     */
    public static Parameter getParameter(@NotNull String parameterName, @NotNull QueryFilterExpression filter, @NotNull Class<?> valueType) {
        Value value = filter.getValue(valueType);
        return getParameter(parameterName, value);
    }

    /**
     * 参数名
     *
     * @param filter          条件
     * @param isParameterName 忽略参数名？
     * @param filterName      条件名
     * @return 忽略参数名，或自定义的参数名，或条件名
     */
    @NotNull
    public static String getParameterName(@NotNull QueryFilterExpression filter, boolean isParameterName, String filterName) {
        String parameterName = filter.getParameterName();
        return getParameterName(isParameterName, parameterName, filterName);
    }

    /**
     * 参数
     *
     * @param parameterName 参数名
     * @param list          [ 条件值 ]
     * @param valueType     参数类型
     * @return [ the {@link Parameter} instance ]
     */
    @NotNull
    public static List<Parameter> getParameters(@NotNull String parameterName, List<QueryFilterInValue> list, @NotNull Class<?> valueType) {
        List<Parameter> result = new ArrayList<>();

        if (list == null) {
            return result;
        }

        String namePrefix = parameterName.trim();
        int index = 0;

        for (QueryFilterInValue filter : list) {
            if (filter == null) {
                continue;
            }

            String name = namePrefix.isEmpty() ? "" : (namePrefix + "_" + (index++));
            Value value = filter.getValue(valueType);

            Parameter parameter = getParameter(name, value);
            if (parameter != null) {
                result.add(parameter);
            }
        }

        return result;
    }

    /**
     * 参数名
     *
     * @param filter          条件
     * @param isParameterName 忽略参数名？
     * @param filterName      条件名
     * @return 忽略参数名，或自定义的参数名，或条件名
     */
    @NotNull
    public static String getParameterName(@NotNull QueryFilterIn filter, boolean isParameterName, String filterName) {
        String parameterName = filter.getParameterName();
        return getParameterName(isParameterName, parameterName, filterName);
    }

    /**
     * 参数
     *
     * @param parameterName 参数名
     * @param filter        条件
     * @param valueType     参数类型
     * @return the {@link Parameter} instance
     */
    public static Parameter getFromParameter(@NotNull String parameterName, @NotNull QueryFilterRange filter, @NotNull Class<?> valueType) {
        Value value = filter.getFromValue(valueType);
        return getParameter(parameterName, value);
    }

    /**
     * 参数名
     *
     * @param filter          条件
     * @param isParameterName 忽略参数名？
     * @param filterName      条件名
     * @return 忽略参数名，或自定义的参数名，或条件名
     */
    @NotNull
    public static String getFromParameterName(@NotNull QueryFilterRange filter, boolean isParameterName, String filterName) {
        String parameterName = filter.getFromParameterName();
        return getParameterName(isParameterName, parameterName, filterName);
    }

    /**
     * 参数
     *
     * @param parameterName 参数名
     * @param filter        条件
     * @param valueType     参数类型
     * @return the {@link Parameter} instance
     */
    public static Parameter getToParameter(@NotNull String parameterName, @NotNull QueryFilterRange filter, @NotNull Class<?> valueType) {
        Value value = filter.getToValue(valueType);
        return getParameter(parameterName, value);
    }

    /**
     * 参数名
     *
     * @param filter          条件
     * @param isParameterName 忽略参数名？
     * @param filterName      条件名
     * @return 忽略参数名，或自定义的参数名，或条件名
     */
    @NotNull
    public static String getToParameterName(@NotNull QueryFilterRange filter, boolean isParameterName, String filterName) {
        String parameterName = filter.getToParameterName();
        return getParameterName(isParameterName, parameterName, filterName);
    }

    /**
     * 参数
     *
     * @param parameterName 参数名
     * @param filter        条件
     * @param valueType     参数类型
     * @return the {@link Parameter} instance
     */
    public static Parameter getParameter(@NotNull String parameterName, @NotNull QueryFilterWildcard filter, @NotNull Class<?> valueType) {
        Value value = filter.getValue(valueType);
        return getParameter(parameterName, value);
    }

    /**
     * 参数名
     *
     * @param filter          条件
     * @param isParameterName 忽略参数名？
     * @param filterName      条件名
     * @return 忽略参数名，或自定义的参数名，或条件名
     */
    @NotNull
    public static String getParameterName(@NotNull QueryFilterWildcard filter, boolean isParameterName, String filterName) {
        String parameterName = filter.getParameterName();
        return getParameterName(isParameterName, parameterName, filterName);
    }

    /**
     * 参数
     *
     * @param parameterName 参数名
     * @param value         值
     * @return the {@link Parameter} instance
     */
    public static Parameter getParameter(@NotNull String parameterName, @NotNull Value value) {
        if (parameterName.isBlank() && value.isNull()) {
            return null;
        }

        Parameter result = new Parameter();

        result.setName(parameterName);
        result.setValue(value);

        return result;
    }

    /**
     * 参数名
     *
     * @param isParameterName 忽略参数名？
     * @param parameterName   自定义的参数名
     * @param filterName      条件名
     * @return 忽略参数名，或自定义的参数名，或条件名
     */
    @NotNull
    public static String getParameterName(boolean isParameterName, String parameterName, String filterName) {
        if (isParameterName) {
            if (parameterName == null || parameterName.isEmpty()) {
                return filterName == null ? "" : filterName;
            } else {
                return parameterName;
            }
        } else {
            return "";
        }
    }

}
