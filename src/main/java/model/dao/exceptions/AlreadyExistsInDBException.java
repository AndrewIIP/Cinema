package model.dao.exceptions;

public class AlreadyExistsInDBException extends Exception {
    public AlreadyExistsInDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistsInDBException(String message) {
        super(message);
    }
}



