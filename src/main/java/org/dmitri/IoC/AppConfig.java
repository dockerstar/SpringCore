package org.dmitri.IoC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserMessageService userMessageService() {
        return new UserMessageService();
    }

    @Bean("print-message")
    public UserMessagePrinter userMessagePrinter(UserMessageService userMessageService) {
        return new UserMessagePrinter(userMessageService());
    }
}
