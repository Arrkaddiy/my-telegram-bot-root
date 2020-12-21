package ru.home.telegram.exception;

public class BotRuntimeException extends RuntimeException {

    public BotRuntimeException() {
    }

    public BotRuntimeException(String message) {
        super(message);
    }
}
