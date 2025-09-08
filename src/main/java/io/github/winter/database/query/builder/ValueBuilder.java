package io.github.winter.database.query.builder;

import io.github.winter.boot.tuple.Value;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Value Builder
 *
 * @author changebooks@qq.com
 */
public final class ValueBuilder {

    private ValueBuilder() {
    }

    /**
     * Build
     *
     * @param valueType       Value Type
     * @param valueString     String Value
     * @param valueInteger    Integer Value
     * @param valueLong       Long Value
     * @param valueBigDecimal BigDecimal Value
     * @param valueDate       Date Value
     * @return the {@link Value} instance
     */
    public static Value build(Class<?> valueType,
                              String valueString, Integer valueInteger, Long valueLong, BigDecimal valueBigDecimal, Date valueDate) {
        if (valueType == null) {
            return null;
        }

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

        return null;
    }

}
