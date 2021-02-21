package ru.home.telegram.facade;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface Facade<T> {

    BotApiMethod<?> route(T t);

}