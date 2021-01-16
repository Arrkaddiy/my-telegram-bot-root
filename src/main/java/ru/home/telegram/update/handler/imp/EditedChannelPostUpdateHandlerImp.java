package ru.home.telegram.update.handler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.update.handler.AbstractUpdateHandler;
import ru.home.telegram.update.handler.intf.EditedChannelPostUpdateHandler;

@Component
@Qualifier(value = "editedChannelPostUpdateHandler")
public class EditedChannelPostUpdateHandlerImp extends AbstractUpdateHandler implements EditedChannelPostUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditedChannelPostUpdateHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(Message message) {
        LOGGER.info("Обработка события EditedChannelPost, объект Message: {}", message);
        return null;
    }
}
