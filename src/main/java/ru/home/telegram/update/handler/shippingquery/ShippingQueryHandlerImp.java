package ru.home.telegram.update.handler.shippingquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.state.State;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "shippingQueryHandler")
public class ShippingQueryHandlerImp extends AbstractUpdateHandler implements ShippingQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingQueryHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(ShippingQuery shippingQuery) {
        LOGGER.info("Обработка события ShippingQuery, объект ShippingQuery Id: {}", shippingQuery.getId());
        User user = getUser(shippingQuery.getFrom());
        try {
            State state = getState(user);
            return state.handleShippingQuery(user, shippingQuery);
        } catch (BotRoutingException bre) {
            LOGGER.error("", bre);
            SendMessage errorMessage = new SendMessage();
            errorMessage.setChatId(String.valueOf(user.getTelegramId()));
            errorMessage.setText("");
            return errorMessage;
        }
    }
}
