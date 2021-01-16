package ru.home.telegram.bot;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.config.ServiceConfiguration;
import ru.home.telegram.update.facade.intf.UpdateFacade;

/**
 * Telegram Bot
 */

@Component
public class WelcomeTelegramBot extends TelegramWebhookBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeTelegramBot.class);
    @Setter(onMethod_ = {@Autowired})
    private UpdateFacade updateFacade;
    @Setter(onMethod_ = {@Autowired})
    private ServiceConfiguration serviceConfiguration;

    /**
     * Телеграм-бот обработчик входящий запросов
     *
     * @param update Вхоядший запрос {@link Update}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update != null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Получен входящий запрос Update Id: {}", update.getUpdateId());
            }

            try {
                return updateFacade.route(update);
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("В ходе обработки запроса возникла ошибка! Exception: {}", e.getMessage());
                }

                e.printStackTrace();
                return null;
            }
        } else {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Входящий запрос Update не может быть null!");
            }

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
