package ru.home.telegram.update.handler.shippingquery;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.constant.UpdateContent;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class ShippingQueryHandlerImpl extends AbstractUpdateHandler<ShippingQuery> implements ShippingQueryHandler {
    private static final String HANDLE_SHIPPING_QUERY = "Обработка события ShippingQuery, объект ShippingQuery: {}";

    public ShippingQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(ShippingQuery shippingQuery) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_SHIPPING_QUERY, shippingQuery);
        } else {
            log.info(HANDLE_SHIPPING_QUERY, shippingQuery.getId());
        }

        User user = getUser(shippingQuery.getFrom());
        return stateRoute(UpdateContent.SHIPPING_QUERY, user, shippingQuery);
    }
}
