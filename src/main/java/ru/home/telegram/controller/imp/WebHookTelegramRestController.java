package ru.home.telegram.controller.imp;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.WebhookBot;
import ru.home.telegram.controller.intf.TelegramRestController;

/**
 * Rest-контроллера запросов от Telegram Bot через WebHook
 * <p>
 * Реализуем интерфейс нашего Rest-контроллера {@link TelegramRestController}
 * Анатация @RestController, говорит нам, что данный класс является Rest-контроллером
 */
@RestController
public class WebHookTelegramRestController implements TelegramRestController {
    @Setter(onMethod_ = {@Autowired})
    private WebhookBot welcomeTelegramBot;

    /**
     * Контроллер входящих Post-запросов от Telegram Bot
     *
     * @param update - Входящий запрос от Telegram Bot {@link Update}
     */
    @PostMapping
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return welcomeTelegramBot.onWebhookUpdateReceived(update);
    }
}
