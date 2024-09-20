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

/*@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "opccielEntityManagerFactory",
        transactionManagerRef = "opccielTransactionManager",
        basePackages = {"com.ged.dao.opcciel1"}
)
@PropertySource({"classpath:application.properties"})
@EnableTransactionManagement*/
public class Opcciel1DbConfig {
    @Bean(name="opccielDataSource")
    @ConfigurationProperties(prefix="spring.opcciel1.datasource")
    public DataSource opccielDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "opccielEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean opccielEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("opccielDataSource") DataSource opccielDataSource) {
        return builder
                .dataSource(opccielDataSource)
                .packages("com.ged.entity.opcciel1")
                .persistenceUnit("opcciel")
                .build();
    }

    @Bean(name = "opccielTransactionManager")
    public PlatformTransactionManager opccielTransactionManager(
            @Qualifier("opccielEntityManagerFactory") EntityManagerFactory opccielEntityManagerFactory) {
        return new JpaTransactionManager(opccielEntityManagerFactory);
    }
}
