package ru.home.telegram.update.handler.channelpost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class ChannelPostHandlerImpl extends AbstractUpdateHandler implements ChannelPostHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelPostHandlerImpl.class);
    private static final String HANDLE_CHANNEL_POST = "Обработка события ChannelPost, объект Message: {}";
    private static final String HANDLE_CHANNEL_POST_ID = "Обработка события ChannelPost, объект Message Id: {}";

    public ChannelPostHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(Message message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(HANDLE_CHANNEL_POST, message);
        } else {
            LOGGER.info(HANDLE_CHANNEL_POST_ID, message.getMessageId());
        }

        User user = getUser(message.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleChannelPost(currentState, user, message);
    }
}
