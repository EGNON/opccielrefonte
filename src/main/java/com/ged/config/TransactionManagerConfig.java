package com.ged.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionManagerConfig {
    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager transactionManager (
            @Qualifier("titrescielTransactionManager") PlatformTransactionManager titrescielTransactionManager,
            @Qualifier("refonteTransactionManager") PlatformTransactionManager refonteTransactionManager) {
        return new ChainedTransactionManager(titrescielTransactionManager, refonteTransactionManager);
    }
}
