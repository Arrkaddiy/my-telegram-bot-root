package ru.home.telegram.update.handler.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "messageHandler")
public class MessageHandlerImp extends AbstractUpdateHandler implements MessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerImp.class);

    public MessageHandlerImp(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    /**
     * Обработка входящего запроса типа Message
     *
     * @param message - Входящий запрос типа {@link Message}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> handle(Message message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события Message, объект Message: {}", message);
        } else {
            LOGGER.info("Обработка события Message, объект Message Id: {}", message.getMessageId());
        }

        State state;
        User user = getUser(message.getFrom());

        try {
            state = getState(user);
        } catch (BotRoutingException bre) {
            LOGGER.error("Ошибка маршрутизации текущей стадии! Exception: {}", bre.getMessage(), bre);
            return getErrorStateMessage(user);
        }

        return state.handleMessage(user, message);
    }
}
