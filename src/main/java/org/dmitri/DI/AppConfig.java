package org.dmitri.DI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    @Bean
    @Primary
    public GreetingService formalGreetingService() {
        return new FormalGreetingService();
    }

    @Bean
    public GreetingService friendlyGreetingService() {
        return new FriendlyGreetingService();
    }

    @Bean
    public GreetingPrinterSetter greetingPrinterSetter() {
        return new GreetingPrinterSetter();
    }

    @Bean
    public GreetingPrinterField greetingPrinterField() {
        return new GreetingPrinterField();
    }

    @Bean
    public GreetingPrinterConstructor greetingPrinterConstructor(GreetingService greetingService) {
        return new GreetingPrinterConstructor(greetingService);
    }
}
