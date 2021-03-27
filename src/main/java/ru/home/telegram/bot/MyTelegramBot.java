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
 * Telegram Bot.
 */
@RequiredArgsConstructor
public class MyTelegramBot extends TelegramWebhookBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyTelegramBot.class);
    private static final String INPUT_UPDATE = "Получен входящий запрос Update: {}";
    private static final String INPUT_UPDATE_ID = "Получен входящий запрос Update Id: {}";
    private static final String INPUT_UPDATE_NULL = "Входящий запрос Update не может быть NULL!";
    private static final String UPDATE_EXCEPTION = "В ходе обработки запроса возникла ошибка! Exception: {}";

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
                LOGGER.debug(INPUT_UPDATE, update);
            } else {
                LOGGER.info(INPUT_UPDATE_ID, update.getUpdateId());
            }

            try {
                return updateFacade.route(update);
            } catch (Exception e) {
                LOGGER.error(UPDATE_EXCEPTION, e.getMessage(), e);
                return null;
            }
        } else {
            LOGGER.error(INPUT_UPDATE_NULL);
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
