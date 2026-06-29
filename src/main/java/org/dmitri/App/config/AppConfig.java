package org.dmitri.App.config;

import org.dmitri.App.command.CommandInterface;
import org.dmitri.App.command.OperationConsoleListener;
import org.dmitri.App.command.processorsCommand.*;
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
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:application-dev.properties")
@Import(OperationProcessConfiguration.class)
public class AppConfig {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public OperationConsoleListener operationConsoleListener(
            Scanner scanner,
            List<CommandInterface> commandInterfaceList
            ) {
        Map<Command, CommandInterface> commandInterfaceMap =
               commandInterfaceList
                       .stream()
                       .collect(Collectors.toMap(
                               CommandInterface::getCommand,
                               commandInterface -> commandInterface
                       ));
        return new OperationConsoleListener(scanner, commandInterfaceMap);
    }

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
