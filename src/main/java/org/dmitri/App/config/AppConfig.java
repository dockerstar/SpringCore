package org.dmitri.App.config;

import org.dmitri.App.model.Command;
import org.dmitri.App.repository.AccountRepository;
import org.dmitri.App.repository.InMemoryAccountRepository;
import org.dmitri.App.repository.InMemoryUserRepository;
import org.dmitri.App.repository.UserRepository;
import org.dmitri.App.service.AccountService;
import org.dmitri.App.service.ConsoleListener;
import org.dmitri.App.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-dev.properties")
public class AppConfig {
    @Bean
    public AccountProperties accountProperties() {
        return new AccountProperties();
    }

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public AccountRepository accountRepository(){
        return new InMemoryAccountRepository();
    }

    @Bean
    public UserService userService(UserRepository userRepository,
                                   AccountRepository accountRepository,
                                   AccountProperties accountProperties) {
        return new UserService(userRepository, accountRepository, accountProperties);
    }

    @Bean
    public AccountService accountService(UserRepository userRepository,
                                         AccountRepository accountRepository,
                                         AccountProperties accountProperties) {
        return new AccountService(userRepository, accountRepository, accountProperties);
    }

    @Bean
    public ConsoleListener consoleListener(UserService userService,
                                           AccountService accountService) {
        return new ConsoleListener(userService, accountService);
    }
}
