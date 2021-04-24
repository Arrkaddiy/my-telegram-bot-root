package ru.home.telegram.update.handler.editedchannelpost;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class EditedChannelPostHandlerImpl extends AbstractUpdateHandler implements EditedChannelPostHandler {
    private static final String HANDLE_EDITED_CHANNEL_POST =
            "Обработка события EditedChannelPost, объект Message: {}";
    private static final String HANDLE_EDITED_CHANNEL_POST_ID =
            "Обработка события EditedChannelPost, объект Message Id: {}";

    public EditedChannelPostHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(Message message) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_EDITED_CHANNEL_POST, message);
        } else {
            log.info(HANDLE_EDITED_CHANNEL_POST_ID, message.getMessageId());
        }

        User user = getUser(message.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleEditChannelPost(currentState, user, message);
    }
}
