package ru.home.telegram.service.hadler.intf;

import org.telegram.telegrambots.meta.generics.TelegramBot;

public interface IHandler<T, B extends TelegramBot> {

    void handle(T t, B b);
}
