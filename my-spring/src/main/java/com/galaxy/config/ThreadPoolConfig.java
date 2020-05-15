package com.galaxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    private static final int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
    private static final int maxPoolSize = 35;
    private static final int keepAliveTime = 3;
    private static final int workQueueCapacity = 30;
    private static final String threadNamePrefix = "Async-Service-";

    @Bean()
   public ThreadPoolTaskExecutor threadPool () {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setQueueCapacity(workQueueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;

    }
}
