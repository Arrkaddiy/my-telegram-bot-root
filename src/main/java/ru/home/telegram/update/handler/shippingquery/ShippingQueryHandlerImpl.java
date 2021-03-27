package ru.home.telegram.update.handler.shippingquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class ShippingQueryHandlerImpl extends AbstractUpdateHandler implements ShippingQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingQueryHandlerImpl.class);

    public ShippingQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(ShippingQuery shippingQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события ShippingQuery, объект ShippingQuery: {}", shippingQuery);
        } else {
            LOGGER.info("Обработка события ShippingQuery, объект ShippingQuery Id: {}", shippingQuery.getId());
        }

        State state;
        User user = getUser(shippingQuery.getFrom());

        try {
            state = getState(user);
        } catch (BotRoutingException bre) {
            LOGGER.error("Ошибка маршрутизации текущей стадии! Exception: {}", bre.getMessage(), bre);
            return getErrorStateMessage(null);
        }

        return state.handleShippingQuery(user, shippingQuery);
    }
}
