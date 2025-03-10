package pt.jorgeduarte.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "pt.jorgeduarte.demo.repositories",
        transactionManagerRef = "transactionManager"
)
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            EntityManagerFactoryBuilder builder) {

        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("pt.jorgeduarte.demo.entities")
                .persistenceUnit("default")
                .build();

        // Force Spring to use the JPA EntityManagerFactory interface
        em.setEntityManagerFactoryInterface(EntityManagerFactory.class);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}