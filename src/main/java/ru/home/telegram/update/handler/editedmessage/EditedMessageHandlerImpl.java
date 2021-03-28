package ru.home.telegram.update.handler.editedmessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class EditedMessageHandlerImpl extends AbstractUpdateHandler implements EditedMessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditedMessageHandlerImpl.class);
    private static final String HANDLE_EDITED_MESSAGE = "Обработка события EditedMessage, объект Message: {}";
    private static final String HANDLE_EDITED_MESSAGE_ID = "Обработка события EditedMessage, объект Message Id: {}";

    public EditedMessageHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(Message message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(HANDLE_EDITED_MESSAGE, message);
        } else {
            LOGGER.info(HANDLE_EDITED_MESSAGE_ID, message.getMessageId());
        }

        User user = getUser(message.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleEditedMessage(currentState, user, message);
    }
}
