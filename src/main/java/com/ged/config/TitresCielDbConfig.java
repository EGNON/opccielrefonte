package com.ged.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "titrescielEntityManagerFactory",
        transactionManagerRef = "titrescielTransactionManager",
        basePackages = {
                "com.ged.dao.titresciel1"
        }
)
public class TitresCielDbConfig {
    @Bean(name="titrescielDataSource")
    @ConfigurationProperties(prefix="spring.titresciel.datasource")
    public DataSource titrescielDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "titrescielEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean titrescielEntityManagerFactory(EntityManagerFactoryBuilder builder,
        @Qualifier("titrescielDataSource") DataSource titrescielDataSource) {
        return builder
                .dataSource(titrescielDataSource)
                .packages(
                        "com.ged.entity.titresciel1"
                )
                .build();
    }

    @Bean(name = "titrescielTransactionManager")
    public PlatformTransactionManager titrescielTransactionManager(
            @Qualifier("titrescielEntityManagerFactory") EntityManagerFactory titrescielEntityManagerFactory) {
        return new JpaTransactionManager(titrescielEntityManagerFactory);
    }
}
