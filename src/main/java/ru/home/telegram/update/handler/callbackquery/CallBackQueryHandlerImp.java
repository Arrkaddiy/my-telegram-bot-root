package ru.home.telegram.update.handler.callbackquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.state.State;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "callBackQueryHandler")
public class CallBackQueryHandlerImp extends AbstractUpdateHandler implements CallBackQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallBackQueryHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события CallbackQuery, объект CallbackQuery: {}", callbackQuery);
        } else {
            LOGGER.info("Обработка события CallbackQuery, объект CallbackQuery Id: {}", callbackQuery.getId());
        }

        State state;
        User user = getUser(callbackQuery.getFrom());

        try {
            state = getState(user);
        } catch (BotRoutingException bre) {
            LOGGER.error("Ошибка маршрутизации текущей стадии! Exception: {}", bre.getMessage(), bre);
            return getErrorStateMessage(user);
        }

        return state.handleCallBackQuery(user, callbackQuery);
    }
}
