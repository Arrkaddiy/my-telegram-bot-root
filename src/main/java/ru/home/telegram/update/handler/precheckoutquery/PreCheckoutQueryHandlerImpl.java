package ru.home.telegram.update.handler.precheckoutquery;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.constant.UpdateContent;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class PreCheckoutQueryHandlerImpl extends AbstractUpdateHandler<PreCheckoutQuery> implements PreCheckoutQueryHandler {
    private static final String HANDLE_PRE_CHECKOUT_QUERY =
            "Обработка события PreCheckoutQuery, объект PreCheckoutQuery: {}";

    public PreCheckoutQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(PreCheckoutQuery preCheckoutQuery) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_PRE_CHECKOUT_QUERY, preCheckoutQuery);
        } else {
            log.info(HANDLE_PRE_CHECKOUT_QUERY, preCheckoutQuery.getId());
        }

        User user = getUser(preCheckoutQuery.getFrom());
        return stateRoute(UpdateContent.PRE_CHECKOUT_QUERY, user, preCheckoutQuery);
    }
}
