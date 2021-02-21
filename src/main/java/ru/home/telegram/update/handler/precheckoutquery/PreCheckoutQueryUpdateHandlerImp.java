package ru.home.telegram.update.handler.precheckoutquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;

@Component
@Qualifier(value = "preCheckoutQueryUpdateHandler")
public class PreCheckoutQueryUpdateHandlerImp implements PreCheckoutQueryUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreCheckoutQueryUpdateHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(PreCheckoutQuery preCheckoutQuery) {
        LOGGER.info("Обработка события PreCheckoutQuery, объект PreCheckoutQuery: {}", preCheckoutQuery);
        return null;
    }
}
