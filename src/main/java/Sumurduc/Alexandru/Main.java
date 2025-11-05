package Sumurduc.Alexandru;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
public class Main {
    static void main() throws Exception {
        // Pornim contextul Spring
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DBConfig.class);

        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        //Try.create(jdbcTemplate);
        //Try.drops(jdbcTemplate);
        context.close();

    }
}

