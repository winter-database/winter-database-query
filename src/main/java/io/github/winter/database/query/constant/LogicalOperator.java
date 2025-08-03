package io.github.winter.database.query.constant;

/**
 * 逻辑与或
 *
 * @author changebooks@qq.com
 */
public interface LogicalOperator {
    /**
     * 与
     */
    int AND = 1;

    /**
     * 或
     */
    int OR = 2;

    /**
     * 逻辑或？
     *
     * @param op 逻辑与或
     * @return True|OR, False|AND, Null|UNDEFINED
     */
    default Boolean isOr(int op) {
        return switch (op) {
            case AND -> false;
            case OR -> true;
            default -> null;
        };
    }

}
