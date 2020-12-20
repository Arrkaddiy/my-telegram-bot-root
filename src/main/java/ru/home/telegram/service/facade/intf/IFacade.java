package ru.home.telegram.service.facade.intf;

import org.telegram.telegrambots.meta.generics.TelegramBot;

public interface IFacade<T, B extends TelegramBot> {

    void handle(T t, B b);
}
