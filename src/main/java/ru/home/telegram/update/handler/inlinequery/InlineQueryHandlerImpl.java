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
    private static final String HANDLE_INLINE_QUERY = "Обработка события InlineQuery, объект InlineQuery: {}";
    private static final String HANDLE_INLINE_QUERY_ID = "Обработка события InlineQuery, объект InlineQuery Id: {}";

    public InlineQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(InlineQuery inlineQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(HANDLE_INLINE_QUERY, inlineQuery);
        } else {
            LOGGER.info(HANDLE_INLINE_QUERY_ID, inlineQuery.getId());
        }

        User user = getUser(inlineQuery.getFrom());
        State state = getState(user);

        return state.handleInlineQuery(user, inlineQuery);
    }
}
