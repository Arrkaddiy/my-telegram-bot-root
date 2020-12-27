package ru.home.telegram.exception;

public class BotMessageHandlerException extends RuntimeException {
    public BotMessageHandlerException() {
    }

    public BotMessageHandlerException(String message) {
        super(message);
    }
}
