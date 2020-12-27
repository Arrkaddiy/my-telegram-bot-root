package ru.home.telegram.service.facade.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.service.facade.intf.IMessageFacade;

public class MessageFacade implements IMessageFacade {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageFacade.class);

    @Override
    public BotApiMethod<?> route(Message message) {
        LOGGER.info("Перенаправление на обработку события Message: {}", message);

        return null;
    }
}
