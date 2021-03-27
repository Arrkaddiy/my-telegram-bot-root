package ru.home.telegram.update.handler.pollanswer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class PollAnswerHandlerImpl extends AbstractUpdateHandler implements PollAnswerHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PollAnswerHandlerImpl.class);

    public PollAnswerHandlerImpl(UserService userService, StateFacade stateFacade) {
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
            return getErrorStateMessage(null);
        }

        return state.handlePollAnswer(user, pollAnswer);
    }
}
