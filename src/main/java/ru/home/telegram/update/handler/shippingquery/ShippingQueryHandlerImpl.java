package ru.home.telegram.update.handler.shippingquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class ShippingQueryHandlerImpl extends AbstractUpdateHandler implements ShippingQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingQueryHandlerImpl.class);
    private static final String HANDLE_SHIPPING_QUERY = "Обработка события ShippingQuery, объект ShippingQuery: {}";
    private static final String HANDLE_SHIPPING_QUERY_ID = "Обработка события ShippingQuery, объект ShippingQuery Id: {}";

    public ShippingQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(ShippingQuery shippingQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(HANDLE_SHIPPING_QUERY, shippingQuery);
        } else {
            LOGGER.info(HANDLE_SHIPPING_QUERY_ID, shippingQuery.getId());
        }

        User user = getUser(shippingQuery.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleShippingQuery(currentState, user, shippingQuery);
    }
}
