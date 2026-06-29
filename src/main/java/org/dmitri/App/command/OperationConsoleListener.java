package org.dmitri.App.command;

import org.dmitri.App.exception.*;
import org.dmitri.App.model.Command;
import org.dmitri.App.service.AccountService;
import org.dmitri.App.service.UserService;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class OperationConsoleListener {
    private final Scanner scanner;
    private final Map<Command, CommandInterface> commandInterfaceMap;

    public OperationConsoleListener(Scanner scanner,
                                    Map<Command, CommandInterface> commandInterfaceMap) {
        this.scanner = scanner;
        this.commandInterfaceMap = commandInterfaceMap;
    }

    public void listen() {
        while (true) {
            Command command = listOperation();
            processOperation(command);
        }
    }

    public Command listOperation() {
        command();
        System.out.print("\nInput Command: ");
        while (true) {
            String command = scanner.nextLine();
            try {
                return Command.valueOf(command);
            } catch (IllegalArgumentException e) {
                System.out.printf("Not this command: %s\n", command);
                command();
                System.out.print("\nInput Command: ");
            }
        }
    }

    public void processOperation(Command command) {
       try {
            var process = commandInterfaceMap.get(command);
            process.execute();
       } catch (NotFoundAccountException | NotNullInputLineException | OneAccountUserException |
                IncorrectAmountValueException | UserAlreadyExistsException | NotFoundUserException e) {
           System.out.println(e.getMessage());
       } catch (InputMismatchException e) {
           System.out.println("Type input incorrect!\n");
           scanner.nextLine();
       }
    }

    public void command() {
        System.out.println("\nCommands: \n _ _ _ _ _ _ _ _");
        for (Command command : Command.values()) {
            System.out.println(command);
        }
        System.out.println(" _ _ _ _ _ _ _ _");
    }
}
