package ru.home.telegram.update.handler.callbackquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class CallBackQueryHandlerImpl extends AbstractUpdateHandler implements CallBackQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallBackQueryHandlerImpl.class);
    private static final String HANDLE_CALL_BACK_QUERY = "Обработка события CallbackQuery, объект CallbackQuery: {}";
    private static final String HANDLE_CALL_BACK_QUERY_ID = "Обработка события CallbackQuery, объект CallbackQuery Id: {}";

    public CallBackQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(HANDLE_CALL_BACK_QUERY, callbackQuery);
        } else {
            LOGGER.info(HANDLE_CALL_BACK_QUERY_ID, callbackQuery.getId());
        }

        User user = getUser(callbackQuery.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleCallBackQuery(currentState, user, callbackQuery);
    }
}
