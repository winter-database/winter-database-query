package io.github.winter.database.query;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * 连表条件
 *
 * @author changebooks@qq.com
 */
public class JoinOn implements Serializable {
    /**
     * 左表名 + 左表字段名
     */
    private String leftName;

    /**
     * 右表名 + 右表字段名
     */
    private String rightName;

    @NotNull
    public String getLeftName() {
        return leftName != null ? leftName : "";
    }

    public void setLeftName(String leftName) {
        this.leftName = leftName != null ? leftName.trim() : "";
    }

    @NotNull
    public String getRightName() {
        return rightName != null ? rightName : "";
    }

    public void setRightName(String rightName) {
        this.rightName = rightName != null ? rightName.trim() : "";
    }

}
