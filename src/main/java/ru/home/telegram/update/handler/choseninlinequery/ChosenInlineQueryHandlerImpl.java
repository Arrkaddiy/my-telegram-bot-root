package ru.home.telegram.update.handler.choseninlinequery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class ChosenInlineQueryHandlerImpl extends AbstractUpdateHandler implements ChosenInlineQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChosenInlineQueryHandlerImpl.class);

    public ChosenInlineQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(ChosenInlineQuery chosenInlineQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события ChosenInlineQuery, объект ChosenInlineQuery: {}", chosenInlineQuery);
        } else {
            LOGGER.info("Обработка события ChosenInlineQuery, объект ChosenInlineQuery ResultId: {}", chosenInlineQuery.getResultId());
        }

        User user = getUser(chosenInlineQuery.getFrom());
        State state = getState(user);

        return state.handleChosenInlineQuery(user, chosenInlineQuery);
    }
}
