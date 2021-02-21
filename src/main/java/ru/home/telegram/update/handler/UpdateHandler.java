package ru.home.telegram.update.handler;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.home.telegram.db.entity.User;

public interface UpdateHandler<T extends BotApiObject> {

    BotApiMethod<?> handle(T t);

}
