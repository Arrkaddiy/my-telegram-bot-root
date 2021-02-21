package ru.home.telegram.update.handler.choseninlinequery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;

@Component
@Qualifier(value = "chosenInlineQueryHandler")
public class ChosenInlineQueryHandlerImp implements ChosenInlineQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChosenInlineQueryHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(ChosenInlineQuery chosenInlineQuery) {
        LOGGER.info("Обработка события ChosenInlineQuery, объект ChosenInlineQuery: {}", chosenInlineQuery);
        return null;
    }
}
