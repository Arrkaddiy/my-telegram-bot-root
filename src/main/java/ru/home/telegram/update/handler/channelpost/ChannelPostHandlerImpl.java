package ru.home.telegram.update.handler.channelpost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class ChannelPostHandlerImpl extends AbstractUpdateHandler implements ChannelPostHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelPostHandlerImpl.class);

    public ChannelPostHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(Message message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события ChannelPost, объект Message: {}", message);
        } else {
            LOGGER.info("Обработка события ChannelPost, объект Message Id: {}", message.getMessageId());
        }

        User user = getUser(message.getFrom());
        State state = getState(user);

        return state.handleChannelPost(user, message);
    }
}
