package org.dmitri.App.command.processorsCommand;

import org.dmitri.App.command.CommandInterface;
import org.dmitri.App.exception.NotFoundUserException;
import org.dmitri.App.exception.NotNullInputLineException;
import org.dmitri.App.exception.UserAlreadyExistsException;
import org.dmitri.App.model.Command;
import org.dmitri.App.model.User;
import org.dmitri.App.service.UserService;

import java.util.List;

public class ShowAllUsersProcessor implements CommandInterface {
    private final UserService userService;

    public ShowAllUsersProcessor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() throws NotNullInputLineException, UserAlreadyExistsException, NotFoundUserException {
        List<User> userList = userService.findAll();
        userList.forEach(System.out::println);
    }

    @Override
    public Command getCommand() {
        return Command.SHOW_ALL_USERS;
    }
}
