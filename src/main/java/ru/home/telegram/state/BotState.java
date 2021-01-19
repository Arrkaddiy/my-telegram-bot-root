package ru.home.telegram.state;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.home.telegram.db.entity.User;

public interface BotState<T extends BotApiObject> {

    BotApiMethod<?> execute(User user, T t);
}
