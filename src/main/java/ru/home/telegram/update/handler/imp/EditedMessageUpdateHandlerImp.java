package ru.home.telegram.update.handler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.update.handler.AbstractUpdateHandler;
import ru.home.telegram.update.handler.intf.EditedMessageUpdateHandler;

@Component
@Qualifier(value = "editedMessageUpdateHandler")
public class EditedMessageUpdateHandlerImp extends AbstractUpdateHandler implements EditedMessageUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditedMessageUpdateHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(Message message) {
        LOGGER.info("Обработка события EditedMessage, объект Message: {}", message);
        return null;
    }
}
