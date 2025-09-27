package io.github.winter.database.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.winter.boot.sql.SqlParameter;
import io.github.winter.database.query.builder.QueryBuilderImpl;
import io.github.winter.database.query.parser.QueryParserImpl;
import io.github.winter.database.template.refresh.RefreshTableSchemaListener;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

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

        QueryBuilder queryBuilder = new QueryBuilderImpl(jdbcTemplate);
        Query query = queryBuilder.build(10009);
        QueryParser queryParser = new QueryParserImpl();
        SqlParameter parameter = queryParser.parse(query);

        String json = writeJson(parameter);
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

}
