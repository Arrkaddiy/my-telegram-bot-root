package ru.home.telegram.update.handler.choseninlinequery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class ChosenInlineQueryHandlerImpl extends AbstractUpdateHandler implements ChosenInlineQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChosenInlineQueryHandlerImpl.class);
    private static final String HANDLE_CHOSEN_INLINE_QUERY = "Обработка события ChosenInlineQuery, объект ChosenInlineQuery: {}";
    private static final String HANDLE_CHOSEN_INLINE_QUERY_ID = "Обработка события ChosenInlineQuery, объект ChosenInlineQuery ResultId: {}";

    public ChosenInlineQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(ChosenInlineQuery chosenInlineQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(HANDLE_CHOSEN_INLINE_QUERY, chosenInlineQuery);
        } else {
            LOGGER.info(HANDLE_CHOSEN_INLINE_QUERY_ID, chosenInlineQuery.getResultId());
        }

        User user = getUser(chosenInlineQuery.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleChosenInlineQuery(currentState, user, chosenInlineQuery);
    }
}
