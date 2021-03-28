package ru.home.telegram.update.handler.precheckoutquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class PreCheckoutQueryHandlerImpl extends AbstractUpdateHandler implements PreCheckoutQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreCheckoutQueryHandlerImpl.class);
    private static final String HANDLE_PRE_CHECKOUT_QUERY = "Обработка события PreCheckoutQuery, объект PreCheckoutQuery: {}";
    private static final String HANDLE_PRE_CHECKOUT_QUERY_ID = "Обработка события PreCheckoutQuery, объект PreCheckoutQuery Id: {}";

    public PreCheckoutQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(PreCheckoutQuery preCheckoutQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(HANDLE_PRE_CHECKOUT_QUERY, preCheckoutQuery);
        } else {
            LOGGER.info(HANDLE_PRE_CHECKOUT_QUERY_ID, preCheckoutQuery.getId());
        }

        User user = getUser(preCheckoutQuery.getFrom());
        State state = getState(user);

        return state.handlePreCheckoutQuery(user, preCheckoutQuery);
    }
}
