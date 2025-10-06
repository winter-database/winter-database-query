package io.github.winter.database.query.entity;

/**
 * Boolean类型转换
 *
 * <pre>
 * 1 to True
 * !1 to False
 * </pre>
 *
 * @author changebooks@qq.com
 */
public final class BooleanCast {
    /**
     * 1-True
     * 0-False
     */
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    private BooleanCast() {
    }

    public static boolean fromInt(Integer value) {
        if (value != null) {
            return value == TRUE;
        } else {
            return false;
        }
    }

}
