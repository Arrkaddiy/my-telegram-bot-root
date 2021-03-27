package ru.home.telegram.bot;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.config.ServiceConfiguration;
import ru.home.telegram.update.facade.UpdateFacade;

/**
 * Telegram Bot
 */
@RequiredArgsConstructor
public class MyTelegramBot extends TelegramWebhookBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyTelegramBot.class);

    private final UpdateFacade updateFacade;
    private final ServiceConfiguration serviceConfiguration;

    /**
     * Телеграм-бот обработчик входящий запросов
     *
     * @param update Вхоядший запрос {@link Update}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update != null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Получен входящий запрос Update: {}", update);
            } else {
                LOGGER.info("Получен входящий запрос Update Id: {}", update.getUpdateId());
            }

            try {
                return updateFacade.route(update);
            } catch (Exception e) {
                LOGGER.error("В ходе обработки запроса возникла ошибка! Exception: {}", e.getMessage(), e);
                return null;
            }
        } else {
            LOGGER.error("Входящий запрос Update не может быть null!");
            return null;
        }
    }

    @Override
    public String getBotPath() {
        return serviceConfiguration.getBotPath();
    }

    @Override
    public String getBotUsername() {
        return serviceConfiguration.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return serviceConfiguration.getBotToken();
    }
}
