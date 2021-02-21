package ru.home.telegram.update.handler.choseninlinequery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.state.State;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "chosenInlineQueryHandler")
public class ChosenInlineQueryHandlerImp extends AbstractUpdateHandler implements ChosenInlineQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChosenInlineQueryHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(ChosenInlineQuery chosenInlineQuery) {
        LOGGER.info("Обработка события ChosenInlineQuery, объект ChosenInlineQuery ResultId: {}", chosenInlineQuery.getResultId());
        User user = getUser(chosenInlineQuery.getFrom());
        try {
            State state = getState(user);
            return state.handleChosenInlineQuery(user, chosenInlineQuery);
        } catch (BotRoutingException bre) {
            LOGGER.error("", bre);
            SendMessage errorMessage = new SendMessage();
            errorMessage.setChatId(String.valueOf(user.getTelegramId()));
            errorMessage.setText("");
            return errorMessage;
        }
    }
}
