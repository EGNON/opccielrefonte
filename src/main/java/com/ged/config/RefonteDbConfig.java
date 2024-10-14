package com.ged.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "refonteEntityManagerFactory",
        transactionManagerRef = "refonteTransactionManager",
        basePackages = {
                "com.ged.dao"
        }
)
@PropertySource({"classpath:application-dev.properties"})
public class RefonteDbConfig {
    @Bean(name="refonteDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource refonteDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "refonteEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean refonteEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("refonteDataSource") DataSource refonteDataSource) {

        return builder
                .dataSource(refonteDataSource)
                .packages(
                        "com.ged.entity"
                )
                .persistenceUnit("refonte")
                .build();
    }

    @Primary
    @Bean(name = "refonteTransactionManager")
    public PlatformTransactionManager refonteTransactionManager(
            @Qualifier("refonteEntityManagerFactory") EntityManagerFactory refonteEntityManagerFactory) {
        return new JpaTransactionManager(refonteEntityManagerFactory);
    }
}
