package io.github.winter.database.query;

import io.github.winter.boot.tuple.Value;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 创建值
 *
 * @author changebooks@qq.com
 */
public final class ValueBuilder {

    private ValueBuilder() {
    }

    /**
     * 创建
     *
     * @param valueType       类型
     * @param valueString     字符串
     * @param valueInteger    整数
     * @param valueLong       长整数
     * @param valueBigDecimal 小数
     * @param valueDate       日期时间
     * @return the {@link Value} instance
     */
    @NotNull
    public static Value build(Class<?> valueType,
                              String valueString, Integer valueInteger, Long valueLong, BigDecimal valueBigDecimal, Date valueDate) {
        if (String.class == valueType) {
            return new Value(valueString);
        }

        if (Integer.class == valueType) {
            return new Value(valueInteger);
        }

        if (Long.class == valueType) {
            return new Value(valueLong);
        }

        if (BigDecimal.class == valueType) {
            return new Value(valueBigDecimal);
        }

        if (Date.class == valueType) {
            return new Value(valueDate);
        }

        throw new RuntimeException(String.format("unsupported value type, type: %s", valueType));
    }

}
