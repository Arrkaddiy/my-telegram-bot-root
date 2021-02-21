package ru.home.telegram.update.handler.callbackquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
        LOGGER.info("Обработка события CallbackQuery, объект CallbackQuery Id: {}", callbackQuery.getId());
        User user = getUser(callbackQuery.getFrom());
        try {
            State state = getState(user);
            return state.handleCallBackQuery(user, callbackQuery);
        } catch (BotRoutingException bre) {
            LOGGER.error("", bre);
            SendMessage errorMessage = new SendMessage();
            errorMessage.setChatId(String.valueOf(user.getTelegramId()));
            errorMessage.setText("");
            return errorMessage;
        }
    }
}
