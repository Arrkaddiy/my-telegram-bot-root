package ru.home.telegram.update.handler.inlinequery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "inlineQueryHandler")
public class InlineQueryHandlerImp extends AbstractUpdateHandler implements InlineQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(InlineQueryHandlerImp.class);

    public InlineQueryHandlerImp(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(InlineQuery inlineQuery) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события InlineQuery, объект InlineQuery: {}", inlineQuery);
        } else {
            LOGGER.info("Обработка события InlineQuery, объект InlineQuery Id: {}", inlineQuery.getId());
        }

        State state;
        User user = getUser(inlineQuery.getFrom());

        try {
            state = getState(user);
        } catch (BotRoutingException bre) {
            LOGGER.error("Ошибка маршрутизации текущей стадии! Exception: {}", bre.getMessage(), bre);
            return getErrorStateMessage(user);
        }

        return state.handleInlineQuery(user, inlineQuery);
    }
}
