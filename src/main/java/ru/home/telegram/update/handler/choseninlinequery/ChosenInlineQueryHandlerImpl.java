package ru.home.telegram.update.handler.choseninlinequery;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.constant.UpdateContent;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class ChosenInlineQueryHandlerImpl extends AbstractUpdateHandler<ChosenInlineQuery> implements ChosenInlineQueryHandler {
    private static final String HANDLE_CHOSEN_INLINE_QUERY =
            "Обработка события ChosenInlineQuery, объект ChosenInlineQuery: {}";

    public ChosenInlineQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(ChosenInlineQuery chosenInlineQuery) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_CHOSEN_INLINE_QUERY, chosenInlineQuery);
        } else {
            log.info(HANDLE_CHOSEN_INLINE_QUERY, chosenInlineQuery.getResultId());
        }

        User user = getUser(chosenInlineQuery.getFrom());
        return stateRoute(UpdateContent.CHOSEN_INLINE_QUERY, user, chosenInlineQuery);
    }
}
