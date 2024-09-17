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
                "com.ged.dao.crm",
                "com.ged.dao.lab",
                "com.ged.dao.standard",
                "com.ged.dao.opcciel",
                "com.ged.dao.titresciel",
                "com.ged.dao.security"
        }
)
@PropertySource({"classpath:application-dev.properties"})
public class RefonteDbConfig {
    @Bean(name="refonteDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.refonte.datasource")
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
                        "com.ged.entity.crm",
                        "com.ged.entity.lab",
                        "com.ged.entity.standard",
                        "com.ged.entity.opcciel",
                        "com.ged.entity.titresciel",
                        "com.ged.entity.security"
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
