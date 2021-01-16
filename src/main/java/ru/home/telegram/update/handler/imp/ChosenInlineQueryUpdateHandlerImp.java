package ru.home.telegram.update.handler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import ru.home.telegram.update.handler.AbstractUpdateHandler;
import ru.home.telegram.update.handler.intf.ChosenInlineQueryUpdateHandler;

@Component
@Qualifier(value = "chosenInlineQueryUpdateHandler")
public class ChosenInlineQueryUpdateHandlerImp extends AbstractUpdateHandler implements ChosenInlineQueryUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChosenInlineQueryUpdateHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(ChosenInlineQuery chosenInlineQuery) {
        LOGGER.info("Обработка события ChosenInlineQuery, объект ChosenInlineQuery: {}", chosenInlineQuery);
        return null;
    }
}
