package ru.home.telegram.service.hadler.intf;

import org.telegram.telegrambots.bots.TelegramWebhookBot;

public interface IWebHookBotHandler<T> extends IHandler<T, TelegramWebhookBot> {
}
