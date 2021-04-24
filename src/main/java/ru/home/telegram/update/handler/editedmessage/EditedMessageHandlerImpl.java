package ru.home.telegram.update.handler.editedmessage;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class EditedMessageHandlerImpl extends AbstractUpdateHandler implements EditedMessageHandler {
    private static final String HANDLE_EDITED_MESSAGE =
            "Обработка события EditedMessage, объект Message: {}";
    private static final String HANDLE_EDITED_MESSAGE_ID =
            "Обработка события EditedMessage, объект Message Id: {}";

    public EditedMessageHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(Message message) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_EDITED_MESSAGE, message);
        } else {
            log.info(HANDLE_EDITED_MESSAGE_ID, message.getMessageId());
        }

        User user = getUser(message.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleEditedMessage(currentState, user, message);
    }
}
