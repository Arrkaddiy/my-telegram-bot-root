package ru.home.telegram.update.handler.inlinequery;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class InlineQueryHandlerImpl extends AbstractUpdateHandler implements InlineQueryHandler {
    private static final String HANDLE_INLINE_QUERY =
            "Обработка события InlineQuery, объект InlineQuery: {}";
    private static final String HANDLE_INLINE_QUERY_ID =
            "Обработка события InlineQuery, объект InlineQuery Id: {}";

    public InlineQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(InlineQuery inlineQuery) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_INLINE_QUERY, inlineQuery);
        } else {
            log.info(HANDLE_INLINE_QUERY_ID, inlineQuery.getId());
        }

        User user = getUser(inlineQuery.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleInlineQuery(currentState, user, inlineQuery);
    }
}
