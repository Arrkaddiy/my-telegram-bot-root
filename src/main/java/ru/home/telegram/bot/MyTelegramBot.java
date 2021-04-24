package ru.home.telegram.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.config.ServiceConfiguration;
import ru.home.telegram.exception.BotRuntimeException;
import ru.home.telegram.update.facade.UpdateFacade;

/**
 * Telegram Bot.
 */
@Slf4j
@RequiredArgsConstructor
public class MyTelegramBot extends TelegramWebhookBot {
    private static final String INPUT_UPDATE =
            "Получен входящий запрос Update: {}";
    private static final String INPUT_UPDATE_ID =
            "Получен входящий запрос Update Id: {}";
    private static final String INPUT_UPDATE_NULL =
            "Получен входящий запрос Update равным NULL!";
    private static final String UPDATE_EXCEPTION_BRE =
            "В ходе обработки запроса возникла ошибка! BotRuntimeException: {}";
    private static final String UPDATE_EXCEPTION =
            "В ходе обработки запроса возникла непредвиденная ошибка! Exception: {}";

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
            if (log.isDebugEnabled()) {
                log.debug(INPUT_UPDATE, update);
            } else {
                log.info(INPUT_UPDATE_ID, update.getUpdateId());
            }

            try {
                return updateFacade.route(update);
            } catch (BotRuntimeException bre) {
                log.error(UPDATE_EXCEPTION_BRE, bre.getMessage(), bre);
            } catch (Exception e) {
                log.error(UPDATE_EXCEPTION, e.getMessage(), e);
            }
        } else {
            log.error(INPUT_UPDATE_NULL);
        }
        return null;
    }

    /**
     * Получение WebHook Telegram Bot'а
     *
     * @return WebHook Telegram Bot'а
     */
    @Override
    public String getBotPath() {
        return serviceConfiguration.getBotPath();
    }

    /**
     * Получение UserName Telegram Bot'а
     *
     * @return UserName Telegram Bot'а
     */
    @Override
    public String getBotUsername() {
        return serviceConfiguration.getBotUsername();
    }

    /**
     * Получение Token Telegram Bot'а
     *
     * @return Token Telegram Bot'а
     */
    @Override
    public String getBotToken() {
        return serviceConfiguration.getBotToken();
    }
}
