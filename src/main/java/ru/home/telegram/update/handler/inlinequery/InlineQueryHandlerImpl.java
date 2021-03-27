package ru.home.telegram.update.handler.inlinequery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class InlineQueryHandlerImpl extends AbstractUpdateHandler implements InlineQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(InlineQueryHandlerImpl.class);

    public InlineQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(InlineQuery inlineQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события InlineQuery, объект InlineQuery: {}", inlineQuery);
        } else {
            LOGGER.info("Обработка события InlineQuery, объект InlineQuery Id: {}", inlineQuery.getId());
        }

        User user = getUser(inlineQuery.getFrom());
        State state = getState(user);

        return state.handleInlineQuery(user, inlineQuery);
    }
}
