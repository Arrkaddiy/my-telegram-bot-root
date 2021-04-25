package ru.home.telegram.state.facade;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.update.constant.UpdateContent;

public interface StateFacade {

    BotApiMethod<?> route(UpdateContent updateContent, User user, BotApiObject botApiObject);

}
