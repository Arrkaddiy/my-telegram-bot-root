package ru.home.telegram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.WebhookBot;

/**
 * Rest-контроллера запросов от Telegram Bot через WebHook
 * <p>
 * Реализуем интерфейс нашего Rest-контроллера {@link TelegramRestController}
 */
@RestController
@RequiredArgsConstructor
public class WebHookTelegramRestController implements TelegramRestController {

    private final WebhookBot myTelegramBot;

    /**
     * Контроллер входящих Post-запросов от Telegram Bot
     *
     * @param update - Входящий запрос от Telegram Bot {@link Update}
     */
    @PostMapping
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return myTelegramBot.onWebhookUpdateReceived(update);
    }
}
