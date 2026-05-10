package com.rollback.api_alunos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {

    @Bean(destroyMethod = "shutdown")
    public ExecutorService riskAnalysisExecutor() {
        int poolSize = Math.max(2, Runtime.getRuntime().availableProcessors());
        return Executors.newFixedThreadPool(poolSize);
    }
}
