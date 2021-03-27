package ru.home.telegram.update.handler;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface UpdateHandler<T extends BotApiObject> {

    BotApiMethod<?> handle(T t);

}
