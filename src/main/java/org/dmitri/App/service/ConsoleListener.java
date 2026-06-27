package org.dmitri.App.service;

import org.dmitri.App.exception.*;
import org.dmitri.App.model.Command;
import org.dmitri.App.model.User;

import java.util.List;
import java.util.Scanner;

public class ConsoleListener {
    private final UserService userService;
    private final AccountService accountService;

    public ConsoleListener(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    public void startListen() {
        Scanner scanner = new Scanner(System.in);
        String inputCommand = lineInput();
        while (!inputCommand.equals(String.valueOf(Command.EXIT))) {
            try {
                switch (inputCommand) {
                    case "USER_CREATE" -> {
                        System.out.print("Input login: ");
                        String login = scanner.nextLine();
                        User user = userService.create(login);
                        System.out.println(user + "\n");
                    }
                    case "SHOW_ALL_USERS" -> {
                        List<User> userList = userService.findAll();
                        userList.stream().forEach(System.out::println);
                    }
                    case "ACCOUNT_CREATE" -> {
                        System.out.print("Введите id пользователя для кого вы хотите открыть счет: ");
                        accountService.create(scanner.nextInt());
                        scanner.nextLine();
                    }
                    case "ACCOUNT_DEPOSIT" -> {
                        System.out.print("Введите id счета для пополнения: ");
                        Integer accountId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Введите сумму пополнения: ");
                        Integer amountDeposit = scanner.nextInt();
                        scanner.nextLine();
                        accountService.deposit(accountId, amountDeposit);
                    }
                    case "ACCOUNT_WITHDRAW" -> {
                        System.out.print("Введите id счета с которого нужно списать сумму: ");
                        Integer accountId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Введите сумму списания: ");
                        Integer amountWithdraw = scanner.nextInt();
                        scanner.nextLine();
                        accountService.withdraw(accountId, amountWithdraw);
                    }
                    case "ACCOUNT_TRANSFER" -> {
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
                    case "ACCOUNT_CLOSE" -> {
                        System.out.print("Введите id счета который нужно удалить: ");
                        Integer accountId = scanner.nextInt();
                        scanner.nextLine();
                        accountService.close(accountId);
                    }
                    default -> {
                        System.out.println("Not this command");
                    }
                }
                inputCommand = lineInput();
            } catch (UserAlreadyExistsException | NotFoundUserException | NotFoundAccountException |
                     IncorrectAmountValueException | OneAccountUserException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
                inputCommand = lineInput();
            }
        }
    }

    public void command() {
        System.out.println("\nCommands: \n _ _ _ _ _ _ _ _");
        for (Command command : Command.values()) {
            System.out.println(command);
        }
        System.out.println(" _ _ _ _ _ _ _ _");
    }

    public String lineInput() {
        command();
        System.out.print("Input command: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
