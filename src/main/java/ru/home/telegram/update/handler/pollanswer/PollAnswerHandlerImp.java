package ru.home.telegram.update.handler.pollanswer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.state.State;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "pollAnswerHandler")
public class PollAnswerHandlerImp extends AbstractUpdateHandler implements PollAnswerHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PollAnswerHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(PollAnswer pollAnswer) {
        LOGGER.info("Обработка события PollAnswer, объект PollAnswer pollId: {}", pollAnswer.getPollId());
        User user = getUser(pollAnswer.getUser());
        try {
            State state = getState(user);
            return state.handlePollAnswer(user, pollAnswer);
        } catch (BotRoutingException bre) {
            LOGGER.error("", bre);
            SendMessage errorMessage = new SendMessage();
            errorMessage.setChatId(String.valueOf(user.getTelegramId()));
            errorMessage.setText("");
            return errorMessage;
        }
    }
}
