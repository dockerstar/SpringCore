package org.dmitri.App.command.processorsCommand;

import org.dmitri.App.command.CommandInterface;
import org.dmitri.App.exception.NotFoundUserException;
import org.dmitri.App.exception.NotNullInputLineException;
import org.dmitri.App.exception.UserAlreadyExistsException;
import org.dmitri.App.model.Command;
import org.dmitri.App.service.AccountService;

import java.util.Scanner;

public class AccountCreateProcessor implements CommandInterface {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountCreateProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void execute() throws NotNullInputLineException, UserAlreadyExistsException, NotFoundUserException {
        System.out.print("Введите id пользователя для кого вы хотите открыть счет: ");
        accountService.create(scanner.nextInt());
        scanner.nextLine();
    }

    @Override
    public Command getCommand() {
        return Command.ACCOUNT_CREATE;
    }
}
