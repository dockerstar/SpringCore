package org.dmitri.App.command.processorsCommand;

import org.dmitri.App.command.CommandInterface;
import org.dmitri.App.exception.*;
import org.dmitri.App.model.Command;
import org.dmitri.App.service.AccountService;

import java.util.Scanner;

public class AccountCloseProcessor implements CommandInterface {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountCloseProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void execute() throws NotNullInputLineException, UserAlreadyExistsException, NotFoundUserException, NotFoundAccountException, IncorrectAmountValueException, OneAccountUserException {
        System.out.print("Введите id счета который нужно удалить: ");
        Integer accountId = scanner.nextInt();
        scanner.nextLine();
        accountService.close(accountId);
    }

    @Override
    public Command getCommand() {
        return Command.ACCOUNT_CLOSE;
    }
}
