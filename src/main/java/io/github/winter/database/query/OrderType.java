package io.github.winter.database.query;

/**
 * 排序方式
 *
 * @author changebooks@qq.com
 */
public final class OrderType {
    /**
     * 升序
     */
    public static final int ASC = 1;

    /**
     * 降序
     */
    public static final int DESC = 2;

    private OrderType() {
    }

    /**
     * 降序？
     *
     * @param type 排序方式
     * @return True|DESC, False|ASC, Null|UNDEFINED
     */
    public static Boolean isDesc(int type) {
        return switch (type) {
            case ASC -> false;
            case DESC -> true;
            default -> null;
        };
    }

}
