package org.dmitri.App.command.processorsCommand;

import org.dmitri.App.command.CommandInterface;
import org.dmitri.App.exception.*;
import org.dmitri.App.model.Command;
import org.dmitri.App.service.AccountService;

import java.util.Scanner;

public class AccountWithdrawProcessor implements CommandInterface {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountWithdrawProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void execute() throws NotNullInputLineException, UserAlreadyExistsException, NotFoundUserException, NotFoundAccountException, IncorrectAmountValueException {
        System.out.print("Введите id счета с которого нужно списать сумму: ");
        Integer accountId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите сумму списания: ");
        Integer amountWithdraw = scanner.nextInt();
        scanner.nextLine();
        accountService.withdraw(accountId, amountWithdraw);
    }

    @Override
    public Command getCommand() {
        return Command.ACCOUNT_WITHDRAW;
    }
}
