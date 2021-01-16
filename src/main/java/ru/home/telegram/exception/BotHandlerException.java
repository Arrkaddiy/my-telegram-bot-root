package ru.home.telegram.exception;

public class BotHandlerException extends BotRuntimeException {

    public BotHandlerException() {
    }

    public BotHandlerException(String message) {
        super(message);
    }
}
