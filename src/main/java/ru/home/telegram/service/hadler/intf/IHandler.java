package ru.home.telegram.service.hadler.intf;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface IHandler<T> {

    BotApiMethod<?> handle(T t);
}
