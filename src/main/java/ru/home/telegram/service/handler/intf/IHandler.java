package ru.home.telegram.service.handler.intf;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface IHandler<T> {

    BotApiMethod<?> handle(T t);

}
