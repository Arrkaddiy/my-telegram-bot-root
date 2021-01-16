package ru.home.telegram.update.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface UpdateHandler<T> {

    BotApiMethod<?> handle(T t);

}
