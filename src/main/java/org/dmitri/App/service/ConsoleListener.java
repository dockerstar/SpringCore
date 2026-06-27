package org.dmitri.App.service;

import org.dmitri.App.model.Command;

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
        command();
        String inputCommand = scanner.nextLine();
        while (!inputCommand.equals(String.valueOf(Command.EXIT))) {
            switch (inputCommand) {
                case "USER_CREATE" -> {
                    System.out.println("create");
                }
            }
        }
    }

    public void command() {
        System.out.println("Enter command:");
        for (Command command:Command.values()) {
            System.out.println(command);
        }
    }
}
