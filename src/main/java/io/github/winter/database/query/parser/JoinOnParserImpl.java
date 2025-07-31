package io.github.winter.database.query.parser;

import io.github.winter.boot.sql.LogicalOperator;
import io.github.winter.database.query.JoinOnParser;
import io.github.winter.database.query.LogicalOperatorEnum;
import io.github.winter.database.query.NameJoiner;

/**
 * @author changebooks@qq.com
 */
public class JoinOnParserImpl implements JoinOnParser {

    @Override
    public String parse(int logicalOperator, String leftTable, String leftColumn, String rightTable, String rightColumn) {
        String operator = getOperator(logicalOperator);
        String joinOn = NameJoiner.join(leftTable, leftColumn) + " = " + NameJoiner.join(rightTable, rightColumn);
        if (operator.isEmpty()) {
            return joinOn;
        } else {
            return operator + " " + joinOn;
        }
    }

    @Override
    public String getOperator(int logicalOperator) {
        return switch (logicalOperator) {
            case LogicalOperatorEnum.AND -> LogicalOperator.AND;
            case LogicalOperatorEnum.OR -> LogicalOperator.OR;
            default -> "";
        };
    }

}
