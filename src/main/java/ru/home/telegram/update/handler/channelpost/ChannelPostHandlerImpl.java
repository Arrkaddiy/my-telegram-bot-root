package ru.home.telegram.update.handler.channelpost;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class ChannelPostHandlerImpl extends AbstractUpdateHandler implements ChannelPostHandler {
    private static final String HANDLE_CHANNEL_POST =
            "Обработка события ChannelPost, объект Message: {}";
    private static final String HANDLE_CHANNEL_POST_ID =
            "Обработка события ChannelPost, объект Message Id: {}";

    public ChannelPostHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(Message message) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_CHANNEL_POST, message);
        } else {
            log.info(HANDLE_CHANNEL_POST_ID, message.getMessageId());
        }

        User user = getUser(message.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleChannelPost(currentState, user, message);
    }
}
