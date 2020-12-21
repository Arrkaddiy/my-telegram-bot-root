package ru.home.telegram.exception;

public class RepositoryExecuteException extends RuntimeException {

    public RepositoryExecuteException() {
    }

    public RepositoryExecuteException(String message) {
        super(message);
    }

}
