package io.github.winter.database.query;

/**
 * 逻辑与或
 *
 * @author changebooks@qq.com
 */
public final class LogicalType {
    /**
     * 与
     */
    public static final int AND = 1;

    /**
     * 或
     */
    public static final int OR = 2;

    private LogicalType() {
    }

    /**
     * 逻辑或？
     *
     * @param type 逻辑与或
     * @return True|OR, False|AND, Null|UNDEFINED
     */
    public static Boolean isOr(int type) {
        return switch (type) {
            case AND -> false;
            case OR -> true;
            default -> null;
        };
    }

}
