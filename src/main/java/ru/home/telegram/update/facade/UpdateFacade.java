package ru.home.telegram.update.facade;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateFacade {

    BotApiMethod<?> route(Update update);
}
