package ru.home.telegram.update.handler.callbackquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class CallBackQueryHandlerImpl extends AbstractUpdateHandler implements CallBackQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallBackQueryHandlerImpl.class);

    public CallBackQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        if (callbackQuery == null) {
            LOGGER.error("Ошибка обработки объекта CallbackQuery! CallbackQuery не может быть NULL!");
            return null;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события CallbackQuery, объект CallbackQuery: {}", callbackQuery);
        } else {
            LOGGER.info("Обработка события CallbackQuery, объект CallbackQuery Id: {}", callbackQuery.getId());
        }

        User user = getUser(callbackQuery.getFrom());
        State state = getState(user);

        return state.handleCallBackQuery(user, callbackQuery);
    }
}
