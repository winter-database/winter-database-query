package io.github.winter.database.query;

/**
 * Boolean类型转换
 *
 * @author changebooks@qq.com
 */
public final class BooleanCast {
    /**
     * 1-True
     * 0-False
     */
    public static final int TRUE = 1;

    private BooleanCast() {
    }

    public static Boolean fromInt(Integer value) {
        if (value != null) {
            return value == TRUE;
        } else {
            return null;
        }
    }

}
