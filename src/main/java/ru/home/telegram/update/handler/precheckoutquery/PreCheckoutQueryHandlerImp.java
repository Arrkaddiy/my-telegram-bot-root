package ru.home.telegram.update.handler.precheckoutquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.state.State;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "preCheckoutQueryHandler")
public class PreCheckoutQueryHandlerImp extends AbstractUpdateHandler implements PreCheckoutQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreCheckoutQueryHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(PreCheckoutQuery preCheckoutQuery) {
        LOGGER.info("Обработка события PreCheckoutQuery, объект PreCheckoutQuery Id: {}", preCheckoutQuery.getId());
        User user = getUser(preCheckoutQuery.getFrom());
        try {
            State state = getState(user);
            return state.handlePreCheckoutQuery(user, preCheckoutQuery);
        } catch (BotRoutingException bre) {
            LOGGER.error("", bre);
            SendMessage errorMessage = new SendMessage();
            errorMessage.setChatId(String.valueOf(user.getTelegramId()));
            errorMessage.setText("");
            return errorMessage;
        }
    }
}
