package ru.home.telegram.update.handler.callbackquery;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class CallBackQueryHandlerImpl extends AbstractUpdateHandler implements CallBackQueryHandler {
    private static final String HANDLE_CALL_BACK_QUERY = "Обработка события CallbackQuery, объект CallbackQuery: {}";

    public CallBackQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_CALL_BACK_QUERY, callbackQuery);
        } else {
            log.info(HANDLE_CALL_BACK_QUERY, callbackQuery.getId());
        }

        User user = getUser(callbackQuery.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleCallBackQuery(currentState, user, callbackQuery);
    }
}
