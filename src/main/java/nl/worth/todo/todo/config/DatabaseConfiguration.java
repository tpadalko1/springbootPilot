package nl.worth.todo.todo.config;

        import liquibase.integration.spring.SpringLiquibase;
        import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
        import org.springframework.boot.context.properties.ConfigurationProperties;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
        import org.springframework.transaction.annotation.EnableTransactionManagement;

        import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = {"nl.worth.todo.todo"})
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/master.xml");
        liquibase.setDataSource(dbDataSource());
        return liquibase;
    }
}
