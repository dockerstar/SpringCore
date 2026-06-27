package org.dmitri.App.exception;

public class NotFoundUserException extends Exception {
    public NotFoundUserException(String message) {
        super(message);
    }
}
