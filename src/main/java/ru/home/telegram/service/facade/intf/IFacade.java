package ru.home.telegram.service.facade.intf;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface IFacade<T> {

    BotApiMethod<?> route(T t);
}
