package io.github.winter.database.query.constant;

/**
 * 排序方式
 *
 * @author changebooks@qq.com
 */
public interface OrderType {
    /**
     * 升序
     */
    int ASC = 1;

    /**
     * 降序
     */
    int DESC = 2;

    /**
     * 降序？
     *
     * @param type 排序方式
     * @return True|DESC, False|ASC, Null|UNDEFINED
     */
    default Boolean isDesc(int type) {
        return switch (type) {
            case ASC -> false;
            case DESC -> true;
            default -> null;
        };
    }

}
