package org.dmitri.App.command.processorsCommand;

import org.dmitri.App.command.CommandInterface;
import org.dmitri.App.exception.*;
import org.dmitri.App.model.Command;
import org.dmitri.App.service.AccountService;

import java.util.Scanner;

public class AccountTransferProcessor implements CommandInterface {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountTransferProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void execute() throws NotNullInputLineException, UserAlreadyExistsException, NotFoundUserException, NotFoundAccountException, IncorrectAmountValueException {
        System.out.print("Введите id счета с которого нужно перевести: ");
        Integer accountFrom = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите id счета на который нужно перевести: ");
        Integer accountTo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите сумму перевода: ");
        Integer amountTransfer = scanner.nextInt();
        scanner.nextLine();
        accountService.transfer(accountFrom, accountTo, amountTransfer);
    }

    @Override
    public Command getCommand() {
        return Command.ACCOUNT_TRANSFER;
    }
}
