package ru.home.telegram.update.handler.inlinequery;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.constant.UpdateContent;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class InlineQueryHandlerImpl extends AbstractUpdateHandler<InlineQuery> implements InlineQueryHandler {
    private static final String HANDLE_INLINE_QUERY = "Обработка события InlineQuery, объект InlineQuery: {}";

    public InlineQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(InlineQuery inlineQuery) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_INLINE_QUERY, inlineQuery);
        } else {
            log.info(HANDLE_INLINE_QUERY, inlineQuery.getId());
        }

        User user = getUser(inlineQuery.getFrom());
        return stateRoute(UpdateContent.INLINE_QUERY, user, inlineQuery);
    }
}
