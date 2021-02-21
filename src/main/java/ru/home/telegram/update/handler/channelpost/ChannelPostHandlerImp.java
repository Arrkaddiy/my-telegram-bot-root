package ru.home.telegram.update.handler.channelpost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Qualifier(value = "channelPostHandler")
public class ChannelPostHandlerImp implements ChannelPostHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelPostHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(Message message) {
        LOGGER.info("Обработка события ChannelPost, объект Message: {}", message);
        return null;
    }
}
