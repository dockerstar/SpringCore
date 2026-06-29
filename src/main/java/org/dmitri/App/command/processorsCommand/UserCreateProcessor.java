package org.dmitri.App.command.processorsCommand;

import org.dmitri.App.command.CommandInterface;
import org.dmitri.App.exception.NotNullInputLineException;
import org.dmitri.App.exception.UserAlreadyExistsException;
import org.dmitri.App.model.Command;
import org.dmitri.App.model.User;
import org.dmitri.App.service.UserService;

import java.util.Scanner;

public class UserCreateProcessor implements CommandInterface {
    private final UserService userService;
    private final Scanner scanner;

    public UserCreateProcessor(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws NotNullInputLineException, UserAlreadyExistsException {
        System.out.print("Input login: ");
        String login = checkNotNullLine(scanner.nextLine());
        User user = userService.create(login);
        System.out.println(user + "\n");
    }

    @Override
    public Command getCommand() {
        return Command.USER_CREATE;
    }

    public String checkNotNullLine(String line) throws NotNullInputLineException {
        if (line.trim().equals("")) {
            throw new NotNullInputLineException("Строка не должна быть пустой!");
        } else {
            return line;
        }
    }
}
