package io.github.winter.database.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 实体
 *
 * @author changebooks@qq.com
 */
public final class Entity {

    private Entity() {
    }

    /**
     * 字段
     */
    public static final class Column implements Serializable {
        /**
         * 表名
         */
        private String tableName;

        /**
         * 字段名
         */
        private String columnName;

        /**
         * 函数类型
         */
        private Integer funcType;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Integer getFuncType() {
            return funcType;
        }

        public void setFuncType(Integer funcType) {
            this.funcType = funcType;
        }

    }

    /**
     * 条件
     */
    public static final class Filter implements Serializable {
        /**
         * 表名
         */
        private String tableName;

        /**
         * 字段名
         */
        private String columnName;

        /**
         * 函数类型
         */
        private Integer funcType;

        /**
         * 条件类型
         */
        private Integer filterType;

        /**
         * 逻辑与或
         */
        private Integer logicalOperator;

        /**
         * 子条件
         */
        private List<Filter> filters;

        /**
         * 表达式
         */
        private FilterExpression filterExpression;

        /**
         * 在列表中
         */
        private FilterIn filterIn;

        /**
         * 空
         */
        private FilterNull filterNull;

        /**
         * 范围
         */
        private FilterRange filterRange;

        /**
         * 模糊匹配
         */
        private FilterWildcard filterWildcard;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Integer getFuncType() {
            return funcType;
        }

        public void setFuncType(Integer funcType) {
            this.funcType = funcType;
        }

        public Integer getFilterType() {
            return filterType;
        }

        public void setFilterType(Integer filterType) {
            this.filterType = filterType;
        }

        public Integer getLogicalOperator() {
            return logicalOperator;
        }

        public void setLogicalOperator(Integer logicalOperator) {
            this.logicalOperator = logicalOperator;
        }

        public List<Filter> getFilters() {
            return filters;
        }

        public void setFilters(List<Filter> filters) {
            this.filters = filters;
        }

        public FilterExpression getFilterExpression() {
            return filterExpression;
        }

        public void setFilterExpression(FilterExpression filterExpression) {
            this.filterExpression = filterExpression;
        }

        public FilterIn getFilterIn() {
            return filterIn;
        }

        public void setFilterIn(FilterIn filterIn) {
            this.filterIn = filterIn;
        }

        public FilterNull getFilterNull() {
            return filterNull;
        }

        public void setFilterNull(FilterNull filterNull) {
            this.filterNull = filterNull;
        }

        public FilterRange getFilterRange() {
            return filterRange;
        }

        public void setFilterRange(FilterRange filterRange) {
            this.filterRange = filterRange;
        }

        public FilterWildcard getFilterWildcard() {
            return filterWildcard;
        }

        public void setFilterWildcard(FilterWildcard filterWildcard) {
            this.filterWildcard = filterWildcard;
        }

    }

    /**
     * 表达式
     */
    public static final class FilterExpression implements Serializable {
        /**
         * 编码
         */
        private Integer expressionCode;

        /**
         * 字符串
         */
        private String valueString;

        /**
         * 整数
         */
        private Integer valueInteger;

        /**
         * 长整数
         */
        private Long valueLong;

        /**
         * 小数
         */
        private BigDecimal valueBigDecimal;

        /**
         * 日期时间
         */
        private Date valueDate;

        public Integer getExpressionCode() {
            return expressionCode;
        }

        public void setExpressionCode(Integer expressionCode) {
            this.expressionCode = expressionCode;
        }

        public String getValueString() {
            return valueString;
        }

        public void setValueString(String valueString) {
            this.valueString = valueString;
        }

        public Integer getValueInteger() {
            return valueInteger;
        }

        public void setValueInteger(Integer valueInteger) {
            this.valueInteger = valueInteger;
        }

        public Long getValueLong() {
            return valueLong;
        }

        public void setValueLong(Long valueLong) {
            this.valueLong = valueLong;
        }

        public BigDecimal getValueBigDecimal() {
            return valueBigDecimal;
        }

        public void setValueBigDecimal(BigDecimal valueBigDecimal) {
            this.valueBigDecimal = valueBigDecimal;
        }

        public Date getValueDate() {
            return valueDate;
        }

        public void setValueDate(Date valueDate) {
            this.valueDate = valueDate;
        }

    }

    /**
     * 在列表中
     */
    public static final class FilterIn implements Serializable {
        /**
         * 取反？
         */
        private Boolean isNot;

        /**
         * 值列表
         */
        private List<FilterInValue> values;

        public Boolean getNot() {
            return isNot;
        }

        public void setNot(Boolean not) {
            isNot = not;
        }

        public List<FilterInValue> getValues() {
            return values;
        }

        public void setValues(List<FilterInValue> values) {
            this.values = values;
        }

    }

    /**
     * 列表值
     */
    public static final class FilterInValue implements Serializable {
        /**
         * 字符串
         */
        private String valueString;

        /**
         * 整数
         */
        private Integer valueInteger;

        /**
         * 长整数
         */
        private Long valueLong;

        /**
         * 小数
         */
        private BigDecimal valueBigDecimal;

        /**
         * 日期时间
         */
        private Date valueDate;

        public String getValueString() {
            return valueString;
        }

        public void setValueString(String valueString) {
            this.valueString = valueString;
        }

        public Integer getValueInteger() {
            return valueInteger;
        }

        public void setValueInteger(Integer valueInteger) {
            this.valueInteger = valueInteger;
        }

        public Long getValueLong() {
            return valueLong;
        }

        public void setValueLong(Long valueLong) {
            this.valueLong = valueLong;
        }

        public BigDecimal getValueBigDecimal() {
            return valueBigDecimal;
        }

        public void setValueBigDecimal(BigDecimal valueBigDecimal) {
            this.valueBigDecimal = valueBigDecimal;
        }

        public Date getValueDate() {
            return valueDate;
        }

        public void setValueDate(Date valueDate) {
            this.valueDate = valueDate;
        }

    }

    /**
     * 空
     */
    public static final class FilterNull implements Serializable {
        /**
         * 取反？
         */
        private Boolean isNot;

        public Boolean getNot() {
            return isNot;
        }

        public void setNot(Boolean not) {
            isNot = not;
        }

    }

    /**
     * 范围
     */
    public static final class FilterRange implements Serializable {
        /**
         * 包含开始？
         */
        private Boolean isIncludeLower;

        /**
         * 包含结束？
         */
        private Boolean isIncludeUpper;

        /**
         * 开始字符串
         */
        private String fromValueString;

        /**
         * 开始整数
         */
        private Integer fromValueInteger;

        /**
         * 开始长整数
         */
        private Long fromValueLong;

        /**
         * 开始小数
         */
        private BigDecimal fromValueBigDecimal;

        /**
         * 开始日期时间
         */
        private Date fromValueDate;

        /**
         * 结束字符串
         */
        private String toValueString;

        /**
         * 结束整数
         */
        private Integer toValueInteger;

        /**
         * 结束长整数
         */
        private Long toValueLong;

        /**
         * 结束小数
         */
        private BigDecimal toValueBigDecimal;

        /**
         * 结束日期时间
         */
        private Date toValueDate;

        public Boolean getIncludeLower() {
            return isIncludeLower;
        }

        public void setIncludeLower(Boolean includeLower) {
            isIncludeLower = includeLower;
        }

        public Boolean getIncludeUpper() {
            return isIncludeUpper;
        }

        public void setIncludeUpper(Boolean includeUpper) {
            isIncludeUpper = includeUpper;
        }

        public String getFromValueString() {
            return fromValueString;
        }

        public void setFromValueString(String fromValueString) {
            this.fromValueString = fromValueString;
        }

        public Integer getFromValueInteger() {
            return fromValueInteger;
        }

        public void setFromValueInteger(Integer fromValueInteger) {
            this.fromValueInteger = fromValueInteger;
        }

        public Long getFromValueLong() {
            return fromValueLong;
        }

        public void setFromValueLong(Long fromValueLong) {
            this.fromValueLong = fromValueLong;
        }

        public BigDecimal getFromValueBigDecimal() {
            return fromValueBigDecimal;
        }

        public void setFromValueBigDecimal(BigDecimal fromValueBigDecimal) {
            this.fromValueBigDecimal = fromValueBigDecimal;
        }

        public Date getFromValueDate() {
            return fromValueDate;
        }

        public void setFromValueDate(Date fromValueDate) {
            this.fromValueDate = fromValueDate;
        }

        public String getToValueString() {
            return toValueString;
        }

        public void setToValueString(String toValueString) {
            this.toValueString = toValueString;
        }

        public Integer getToValueInteger() {
            return toValueInteger;
        }

        public void setToValueInteger(Integer toValueInteger) {
            this.toValueInteger = toValueInteger;
        }

        public Long getToValueLong() {
            return toValueLong;
        }

        public void setToValueLong(Long toValueLong) {
            this.toValueLong = toValueLong;
        }

        public BigDecimal getToValueBigDecimal() {
            return toValueBigDecimal;
        }

        public void setToValueBigDecimal(BigDecimal toValueBigDecimal) {
            this.toValueBigDecimal = toValueBigDecimal;
        }

        public Date getToValueDate() {
            return toValueDate;
        }

        public void setToValueDate(Date toValueDate) {
            this.toValueDate = toValueDate;
        }

    }

    /**
     * 模糊匹配
     */
    public static final class FilterWildcard implements Serializable {
        /**
         * 取反？
         */
        private Boolean isNot;

        /**
         * 编码
         */
        private Integer wildcardCode;

        /**
         * 字符串
         */
        private String valueString;

        /**
         * 整数
         */
        private Integer valueInteger;

        /**
         * 长整数
         */
        private Long valueLong;

        /**
         * 小数
         */
        private BigDecimal valueBigDecimal;

        /**
         * 日期时间
         */
        private Date valueDate;

        public Boolean getNot() {
            return isNot;
        }

        public void setNot(Boolean not) {
            isNot = not;
        }

        public Integer getWildcardCode() {
            return wildcardCode;
        }

        public void setWildcardCode(Integer wildcardCode) {
            this.wildcardCode = wildcardCode;
        }

        public String getValueString() {
            return valueString;
        }

        public void setValueString(String valueString) {
            this.valueString = valueString;
        }

        public Integer getValueInteger() {
            return valueInteger;
        }

        public void setValueInteger(Integer valueInteger) {
            this.valueInteger = valueInteger;
        }

        public Long getValueLong() {
            return valueLong;
        }

        public void setValueLong(Long valueLong) {
            this.valueLong = valueLong;
        }

        public BigDecimal getValueBigDecimal() {
            return valueBigDecimal;
        }

        public void setValueBigDecimal(BigDecimal valueBigDecimal) {
            this.valueBigDecimal = valueBigDecimal;
        }

        public Date getValueDate() {
            return valueDate;
        }

        public void setValueDate(Date valueDate) {
            this.valueDate = valueDate;
        }

    }

    /**
     * 连表
     */
    public static final class Join implements Serializable {
        /**
         * 表名
         */
        private String joinTable;

        /**
         * 连表方式
         */
        private Integer joinType;

        /**
         * 条件列表
         */
        private List<JoinOn> joinOns;

        public String getJoinTable() {
            return joinTable;
        }

        public void setJoinTable(String joinTable) {
            this.joinTable = joinTable;
        }

        public Integer getJoinType() {
            return joinType;
        }

        public void setJoinType(Integer joinType) {
            this.joinType = joinType;
        }

        public List<JoinOn> getJoinOns() {
            return joinOns;
        }

        public void setJoinOns(List<JoinOn> joinOns) {
            this.joinOns = joinOns;
        }

    }

    /**
     * 连表条件
     */
    public static final class JoinOn implements Serializable {
        /**
         * 左表名
         */
        private String leftTable;

        /**
         * 左表字段名
         */
        private String leftColumn;

        /**
         * 右表名
         */
        private String rightTable;

        /**
         * 右表字段名
         */
        private String rightColumn;

        public String getLeftTable() {
            return leftTable;
        }

        public void setLeftTable(String leftTable) {
            this.leftTable = leftTable;
        }

        public String getLeftColumn() {
            return leftColumn;
        }

        public void setLeftColumn(String leftColumn) {
            this.leftColumn = leftColumn;
        }

        public String getRightTable() {
            return rightTable;
        }

        public void setRightTable(String rightTable) {
            this.rightTable = rightTable;
        }

        public String getRightColumn() {
            return rightColumn;
        }

        public void setRightColumn(String rightColumn) {
            this.rightColumn = rightColumn;
        }

    }

    /**
     * 分组
     */
    public static final class Group implements Serializable {
        /**
         * 表名
         */
        private String tableName;

        /**
         * 字段名
         */
        private String columnName;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

    }

    /**
     * 排序
     */
    public static final class Order implements Serializable {
        /**
         * 表名
         */
        private String tableName;

        /**
         * 字段名
         */
        private String columnName;

        /**
         * 函数类型
         */
        private Integer funcType;

        /**
         * 排序方式
         */
        private Integer orderType;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Integer getFuncType() {
            return funcType;
        }

        public void setFuncType(Integer funcType) {
            this.funcType = funcType;
        }

        public Integer getOrderType() {
            return orderType;
        }

        public void setOrderType(Integer orderType) {
            this.orderType = orderType;
        }

    }

}
