package ru.home.telegram.update.handler.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class MessageHandlerImpl extends AbstractUpdateHandler implements MessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerImpl.class);
    private static final String HANDLE_MESSAGE = "Обработка события Message, объект Message: {}";
    private static final String HANDLE_MESSAGE_ID = "Обработка события Message, объект Message Id: {}";

    public MessageHandlerImpl(UserService userService, StateFacade stateFacade) {
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
            LOGGER.debug(HANDLE_MESSAGE, message);
        } else {
            LOGGER.info(HANDLE_MESSAGE_ID, message.getMessageId());
        }

        User user = getUser(message.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleMessage(currentState, user, message);
    }
}
