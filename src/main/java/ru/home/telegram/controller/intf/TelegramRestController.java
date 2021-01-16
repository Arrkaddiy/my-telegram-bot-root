package ru.home.telegram.controller.intf;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Интерфейс Rest-контроллера запросов от Telegram Bot
 */
public interface TelegramRestController {

    BotApiMethod<?> onUpdateReceived(Update update);
}
