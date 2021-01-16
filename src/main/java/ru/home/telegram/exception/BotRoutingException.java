package ru.home.telegram.exception;

public class BotRoutingException extends BotRuntimeException {

    public BotRoutingException() {
    }

    public BotRoutingException(String message) {
        super(message);
    }
}
