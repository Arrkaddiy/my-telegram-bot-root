package ru.home.telegram.update.handler.editedmessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Qualifier(value = "editedMessageHandler")
public class EditedMessageHandlerImp implements EditedMessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditedMessageHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(Message message) {
        LOGGER.info("Обработка события EditedMessage, объект Message: {}", message);
        return null;
    }
}
