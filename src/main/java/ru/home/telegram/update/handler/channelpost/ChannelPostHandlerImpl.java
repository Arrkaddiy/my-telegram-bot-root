package ru.home.telegram.update.handler.channelpost;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.constant.UpdateContent;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class ChannelPostHandlerImpl extends AbstractUpdateHandler<Message> implements ChannelPostHandler {
    private static final String HANDLE_CHANNEL_POST = "Обработка события ChannelPost, объект Message: {}";

    public ChannelPostHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(Message message) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_CHANNEL_POST, message);
        } else {
            log.info(HANDLE_CHANNEL_POST, message.getMessageId());
        }

        User user = getUser(message.getFrom());
        return stateRoute(UpdateContent.CHANNEL_POST, user, message);
    }
}
