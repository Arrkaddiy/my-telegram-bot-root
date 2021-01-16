package ru.home.telegram.update.handler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.home.telegram.update.handler.AbstractUpdateHandler;
import ru.home.telegram.update.handler.intf.CallBackQueryUpdateHandler;

@Component
@Qualifier(value = "callBackQueryUpdateHandler")
public class CallBackQueryUpdateHandlerImp extends AbstractUpdateHandler implements CallBackQueryUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallBackQueryUpdateHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        LOGGER.info("Обработка события CallbackQuery, объект CallbackQuery: {}", callbackQuery);
        return null;
    }
}
