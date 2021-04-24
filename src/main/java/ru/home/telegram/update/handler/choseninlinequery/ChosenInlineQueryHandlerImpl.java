package ru.home.telegram.update.handler.choseninlinequery;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class ChosenInlineQueryHandlerImpl extends AbstractUpdateHandler implements ChosenInlineQueryHandler {
    private static final String HANDLE_CHOSEN_INLINE_QUERY =
            "Обработка события ChosenInlineQuery, объект ChosenInlineQuery: {}";
    private static final String HANDLE_CHOSEN_INLINE_QUERY_ID =
            "Обработка события ChosenInlineQuery, объект ChosenInlineQuery ResultId: {}";

    public ChosenInlineQueryHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(ChosenInlineQuery chosenInlineQuery) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_CHOSEN_INLINE_QUERY, chosenInlineQuery);
        } else {
            log.info(HANDLE_CHOSEN_INLINE_QUERY_ID, chosenInlineQuery.getResultId());
        }

        User user = getUser(chosenInlineQuery.getFrom());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handleChosenInlineQuery(currentState, user, chosenInlineQuery);
    }
}
