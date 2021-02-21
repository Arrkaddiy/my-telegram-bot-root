package ru.home.telegram.update.handler.inlinequery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;

@Component
@Qualifier(value = "inlineQueryUpdateHandler")
public class InlineQueryUpdateHandlerImp implements InlineQueryUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(InlineQueryUpdateHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(InlineQuery inlineQuery) {
        LOGGER.info("Обработка события InlineQuery, объект InlineQuery: {}", inlineQuery);
        return null;
    }
}
