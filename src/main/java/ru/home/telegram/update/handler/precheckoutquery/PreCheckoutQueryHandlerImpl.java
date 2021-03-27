package ru.home.telegram.update.handler.precheckoutquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class PreCheckoutQueryHandlerImpl extends AbstractUpdateHandler implements PreCheckoutQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreCheckoutQueryHandlerImpl.class);

    public PreCheckoutQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(PreCheckoutQuery preCheckoutQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события PreCheckoutQuery, объект PreCheckoutQuery: {}", preCheckoutQuery);
        } else {
            LOGGER.info("Обработка события PreCheckoutQuery, объект PreCheckoutQuery Id: {}", preCheckoutQuery.getId());
        }

        State state;
        User user = getUser(preCheckoutQuery.getFrom());

        try {
            state = getState(user);
        } catch (BotRoutingException bre) {
            LOGGER.error("Ошибка маршрутизации текущей стадии! Exception: {}", bre.getMessage(), bre);
            return getErrorStateMessage(null);
        }

        return state.handlePreCheckoutQuery(user, preCheckoutQuery);
    }
}
