package com.ged.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

//@Configuration
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "serverEntityManagerFactory",
//        basePackages = {"com.ged.crm.dao", "com.ged.entity.security.token", "com.ged.lab.dao"}
//)
public class ServerDbConfig {
    @Bean(name="serverDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.server.datasource")
    public DataSource serverDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "serverEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean serverEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("serverDataSource") DataSource serverDataSource) {
        return builder
                .dataSource(serverDataSource)
                .packages("com.ged.crm.entity", "com.ged.entity.security.token", "com.ged.lab.entity")
                .build();
    }
}
