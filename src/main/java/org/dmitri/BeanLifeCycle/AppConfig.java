package org.dmitri.BeanLifeCycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    public AppLogger appLogger() {
        return new AppLogger();
    }

    @Bean
    @Scope("prototype")
    public OperationContext operationContext() {
        return new OperationContext();
    }
}
