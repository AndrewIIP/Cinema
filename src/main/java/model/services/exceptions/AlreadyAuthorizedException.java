package model.services.exceptions;

public class AlreadyAuthorizedException extends Exception {
    public AlreadyAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyAuthorizedException(String message) {
        super(message);
    }
}