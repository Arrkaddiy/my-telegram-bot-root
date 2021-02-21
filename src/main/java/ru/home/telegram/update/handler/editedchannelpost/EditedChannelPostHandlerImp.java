package ru.home.telegram.update.handler.editedchannelpost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.state.State;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "editedChannelPostHandler")
public class EditedChannelPostHandlerImp extends AbstractUpdateHandler implements EditedChannelPostHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditedChannelPostHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(Message message) {
        LOGGER.info("Обработка события EditedChannelPost, объект Message Id: {}", message.getMessageId());
        User user = getUser(message.getFrom());
        try {
            State state = getState(user);
            return state.handleEditChannelPost(user, message);
        } catch (BotRoutingException bre) {
            LOGGER.error("", bre);
            SendMessage errorMessage = new SendMessage();
            errorMessage.setChatId(String.valueOf(message.getChatId()));
            errorMessage.setText("");
            return errorMessage;
        }
    }
}
