package ru.home.telegram.update.handler.pollanswer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Component
@Qualifier(value = "pollAnswerHandler")
public class PollAnswerHandlerImp extends AbstractUpdateHandler implements PollAnswerHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PollAnswerHandlerImp.class);

    public PollAnswerHandlerImp(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(PollAnswer pollAnswer) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события PollAnswer, объект PollAnswer: {}", pollAnswer);
        } else {
            LOGGER.info("Обработка события PollAnswer, объект PollAnswer pollId: {}", pollAnswer.getPollId());
        }

        State state;
        User user = getUser(pollAnswer.getUser());

        try {
            state = getState(user);
        } catch (BotRoutingException bre) {
            LOGGER.error("Ошибка маршрутизации текущей стадии! Exception: {}", bre.getMessage(), bre);
            return getErrorStateMessage(user);
        }

        return state.handlePollAnswer(user, pollAnswer);
    }
}
