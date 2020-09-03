package com.pracore.user.config;

import com.pracore.user.services.LogService;
import com.pracore.user.services.SentryLog;
import com.pracore.user.utils.Utility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean(name = "utility")
//    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public Utility getUtility() {
        return new Utility(getLogService());
    }

    @Bean(name = "logService")
    public LogService getLogService() {
        return new SentryLog();
    }
}
