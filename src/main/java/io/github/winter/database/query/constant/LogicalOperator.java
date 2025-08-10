package io.github.winter.database.query.constant;

/**
 * 逻辑与或
 *
 * @author changebooks@qq.com
 */
public final class LogicalOperator {
    /**
     * 与
     */
    public static final int AND = 1;

    /**
     * 或
     */
    public static final int OR = 2;

    /**
     * 逻辑或？
     *
     * @param op 逻辑与或
     * @return True|OR, False|AND, Null|UNDEFINED
     */
    public static Boolean isOr(int op) {
        return switch (op) {
            case AND -> false;
            case OR -> true;
            default -> null;
        };
    }

}
