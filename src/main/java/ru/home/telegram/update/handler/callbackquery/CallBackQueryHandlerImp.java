package ru.home.telegram.update.handler.callbackquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
@Qualifier(value = "callBackQueryHandler")
public class CallBackQueryHandlerImp implements CallBackQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallBackQueryHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        LOGGER.info("Обработка события CallbackQuery, объект CallbackQuery: {}", callbackQuery);
        return null;
    }
}
