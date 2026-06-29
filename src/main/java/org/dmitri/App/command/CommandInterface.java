package org.dmitri.App.command;

import org.dmitri.App.exception.*;
import org.dmitri.App.model.Command;

public interface CommandInterface {
    void execute() throws NotNullInputLineException, UserAlreadyExistsException, NotFoundUserException, NotFoundAccountException, IncorrectAmountValueException, OneAccountUserException;
    Command getCommand();
}
