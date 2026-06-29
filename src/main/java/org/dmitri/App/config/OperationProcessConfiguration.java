package org.dmitri.App.config;

import org.dmitri.App.command.processorsCommand.*;
import org.dmitri.App.service.AccountService;
import org.dmitri.App.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class OperationProcessConfiguration {
    @Bean
    public UserCreateProcessor userCreateProcessor(UserService userService, Scanner scanner
    ) {
        return new UserCreateProcessor(userService, scanner);
    }

    @Bean
    public ShowAllUsersProcessor showAllUsersProcessor(
            UserService userService
    ) {
        return new ShowAllUsersProcessor(userService);
    }

    @Bean
    public AccountCreateProcessor accountCreateProcessor(
            Scanner scanner, AccountService accountService
    ) {
        return new AccountCreateProcessor(scanner, accountService);
    }

    @Bean
    public AccountDepositProcessor accountDepositProcessor(
            Scanner scanner, AccountService accountService
    ) {
        return new AccountDepositProcessor(scanner, accountService);
    }

    @Bean
    public AccountWithdrawProcessor accountWithdrawProcessor(
            Scanner scanner, AccountService accountService
    ) {
        return new AccountWithdrawProcessor(scanner, accountService);
    }

    @Bean
    public AccountTransferProcessor accountTransferProcessor(
            Scanner scanner, AccountService accountService
    ) {
        return new AccountTransferProcessor(scanner, accountService);
    }

    @Bean
    public AccountCloseProcessor accountCloseProcessor(
            Scanner scanner, AccountService accountService
    ) {
        return new AccountCloseProcessor(scanner, accountService);
    }
}
