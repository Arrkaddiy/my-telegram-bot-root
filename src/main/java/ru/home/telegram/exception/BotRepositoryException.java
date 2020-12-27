package ru.home.telegram.exception;

public class BotRepositoryException extends RuntimeException {

    public BotRepositoryException() {
    }

    public BotRepositoryException(String message) {
        super(message);
    }

}
