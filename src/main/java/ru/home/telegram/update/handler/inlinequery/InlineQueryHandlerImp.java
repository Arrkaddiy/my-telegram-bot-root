package ru.home.telegram.update.handler.inlinequery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.state.State;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "inlineQueryHandler")
public class InlineQueryHandlerImp extends AbstractUpdateHandler implements InlineQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(InlineQueryHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(InlineQuery inlineQuery) {
        LOGGER.info("Обработка события InlineQuery, объект InlineQuery Id: {}", inlineQuery.getId());
        User user = getUser(inlineQuery.getFrom());
        try {
            State state = getState(user);
            return state.handleInlineQuery(user, inlineQuery);
        } catch (BotRoutingException bre) {
            LOGGER.error("", bre);
            SendMessage errorMessage = new SendMessage();
            errorMessage.setChatId(String.valueOf(user.getTelegramId()));
            errorMessage.setText("");
            return errorMessage;
        }
    }
}
