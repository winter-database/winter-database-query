package io.github.winter.database.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.winter.database.executor.Executor;
import io.github.winter.database.query.entity.WhereReader;
import io.github.winter.database.template.Template;
import io.github.winter.database.template.refresh.RefreshTableSchemaListener;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Main implements CommandLineRunner {

    @Resource
    private ApplicationEventPublisher publisher;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws SQLException {
        JdbcTemplate jdbcTemplate = jdbcTemplate();

        RefreshTableSchemaListener refreshTableSchemaListener = new RefreshTableSchemaListener(publisher, jdbcTemplate);
        refreshTableSchemaListener.refreshAll();

        Executor executor = new Executor(jdbcTemplate);
        Template template = new Template(executor);

        WhereReader whereReader = new WhereReader();
        List<Entity.Filter> list = whereReader.read(template, 1, 0);

        String json = writeJson(list);
        System.out.println(json);
    }

    public JdbcTemplate jdbcTemplate() {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/query?useUnicode=true&characterEncoding=UTF-8&useSSL=false")
                .username("root")
                .password("123456")
                .build();
        return new JdbcTemplate(dataSource);
    }

    private String writeJson(Object value) {
        ObjectMapper parser = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return parser.writeValueAsString(value);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }


//    public static void main(String[] args) throws JsonProcessingException {
//        SqlParser sqlParser = new SqlParserImpl();
//
//        List<BaseFilter> filters = new ArrayList<>();
//
//        ExpressionFilter filter1 = new ExpressionFilter();
//        filter1.setCode(ExpressionCode.EQ);
//        filter1.setName("name");
//        Parameter parameter1 = new Parameter();
////        parameter1.setName("parameterName");
//        parameter1.setValue(new Value("a'b'c"));
//        filter1.setParameter(parameter1);
//        filters.add(filter1);
//
//        ExpressionFilter filter2 = new ExpressionFilter();
//        filter2.setCode(ExpressionCode.NE);
//        filter2.setName("name2");
//        Parameter parameter2 = new Parameter();
////        parameter2.setName("parameterName2");
//        parameter2.setValue(new Value(new BigDecimal("1.01")));
//        filter2.setParameter(parameter2);
//        filters.add(filter2);
//
//        InFilter filter3 = new InFilter();
//        filter3.setName("name3");
//        List<Parameter> parameter3 = new ArrayList<>();
//        Parameter parameter31 = new Parameter();
////        parameter31.setName("parameterName3");
//        parameter31.setValue(new Value(1));
//        parameter3.add(parameter31);
//        Parameter parameter32 = new Parameter();
////        parameter32.setName("parameterName4");
//        parameter32.setValue(new Value(2L));
//        parameter3.add(parameter32);
//        Parameter parameter33 = new Parameter();
////        parameter33.setName("parameterName5");
//        parameter33.setValue(new Value(new Date()));
//        parameter3.add(parameter33);
//        filter3.setParameters(parameter3);
//        filters.add(filter3);
//
//        SqlParameter sqlParameter = sqlParser.parseSelect("test", false, "id, name", filters, null, null);
//
//        String jsonStr = PARSER.writeValueAsString(sqlParameter);
//        System.out.println(jsonStr);
//
////        CharEscaper charEscaper = new CharEscaper();
////        String escaped = charEscaper.escape("a'b'c");
////        System.out.println(escaped);
//    }

}
