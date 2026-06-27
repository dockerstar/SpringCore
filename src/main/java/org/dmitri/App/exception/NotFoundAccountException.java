package org.dmitri.App.exception;

public class NotFoundAccountException extends RuntimeException {
    public NotFoundAccountException(String message) {
        super(message);
    }
}
