package ru.home.telegram.controller.imp;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.bot.MyTelegramBot;
import ru.home.telegram.controller.intf.IRestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Rest-контроллера запросов от Telegram Bot через WebHook
 * <p>
 * Реализуем интерфейс нашего Rest-контроллера {@link IRestController}
 * Анатация @RestController, говорит нам, что данный класс является Rest-контроллером
 */

@RestController
@AllArgsConstructor
public class WebHookRestController implements IRestController {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookRestController.class);
    //Bot-обработчик входящих запросов
    private MyTelegramBot myTelegramBot;

    /**
     * Обработка входящего запроса от Telegram Bot
     *
     * @param update - Входящий запрос от Telegram Bot {@link Update}
     */
    public void onUpdateReceived(Update update) {
        LOGGER.info("Получен входящий запрос Update: {}", update);
        myTelegramBot.onWebhookUpdateReceived(update);
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("WebHookRestController initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("WebHookRestController destroy");
    }
}
