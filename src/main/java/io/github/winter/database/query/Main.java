package io.github.winter.database.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.winter.boot.filter.*;
import io.github.winter.boot.sql.SqlParameter;
import io.github.winter.boot.sql.SqlParser;
import io.github.winter.boot.sql.parser.SqlParserImpl;
import io.github.winter.boot.tuple.Value;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    /**
     * Parser
     */
    private static final ObjectMapper PARSER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static void main(String[] args) throws JsonProcessingException {
        SqlParser sqlParser = new SqlParserImpl();

        List<BaseFilter> filters = new ArrayList<>();

        ExpressionFilter filter1 = new ExpressionFilter();
        filter1.setCode(ExpressionCode.EQ);
        filter1.setName("name");
        Parameter parameter1 = new Parameter();
//        parameter1.setName("parameterName");
        parameter1.setValue(new Value("a'b'c"));
        filter1.setParameter(parameter1);
        filters.add(filter1);

        ExpressionFilter filter2 = new ExpressionFilter();
        filter2.setCode(ExpressionCode.NE);
        filter2.setName("name2");
        Parameter parameter2 = new Parameter();
//        parameter2.setName("parameterName2");
        parameter2.setValue(new Value(new BigDecimal("1.01")));
        filter2.setParameter(parameter2);
        filters.add(filter2);

        InFilter filter3 = new InFilter();
        filter3.setName("name3");
        List<Parameter> parameter3 = new ArrayList<>();
        Parameter parameter31 = new Parameter();
//        parameter31.setName("parameterName3");
        parameter31.setValue(new Value(1));
        parameter3.add(parameter31);
        Parameter parameter32 = new Parameter();
//        parameter32.setName("parameterName4");
        parameter32.setValue(new Value(2L));
        parameter3.add(parameter32);
        Parameter parameter33 = new Parameter();
//        parameter33.setName("parameterName5");
        parameter33.setValue(new Value(new Date()));
        parameter3.add(parameter33);
        filter3.setParameters(parameter3);
        filters.add(filter3);

        SqlParameter sqlParameter = sqlParser.parseSelect("test", false, "id, name", filters, null, null);

        String jsonStr = PARSER.writeValueAsString(sqlParameter);
        System.out.println(jsonStr);

//        CharEscaper charEscaper = new CharEscaper();
//        String escaped = charEscaper.escape("a'b'c");
//        System.out.println(escaped);
    }

}
