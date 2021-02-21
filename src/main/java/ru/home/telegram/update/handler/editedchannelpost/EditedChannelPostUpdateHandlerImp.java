package ru.home.telegram.update.handler.editedchannelpost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Qualifier(value = "editedChannelPostUpdateHandler")
public class EditedChannelPostUpdateHandlerImp implements EditedChannelPostUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditedChannelPostUpdateHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(Message message) {
        LOGGER.info("Обработка события EditedChannelPost, объект Message: {}", message);
        return null;
    }
}
