package io.github.winter.database.query.builder;

import io.github.winter.boot.sql.Preconditions;
import io.github.winter.boot.tuple.Value;
import jakarta.validation.constraints.NotNull;

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
    @NotNull
    public static Value build(Class<?> valueType,
                              String valueString, Integer valueInteger, Long valueLong, BigDecimal valueBigDecimal, Date valueDate) {
        Preconditions.requireNonNull(valueType, "valueType must not be null");

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
