package ru.home.telegram.update.handler.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "messageHandler")
public class MessageHandlerImp extends AbstractUpdateHandler implements MessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerImp.class);

    /**
     * Обработка входящего запроса типа Message
     *
     * @param message - Входящий запрос типа {@link Message}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> handle(Message message) {
        LOGGER.info("Обработка события Message, объект Message: {}", message);
        return null;
    }
}
