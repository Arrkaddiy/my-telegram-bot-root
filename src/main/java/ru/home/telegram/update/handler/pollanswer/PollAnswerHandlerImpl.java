package ru.home.telegram.update.handler.pollanswer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

public class PollAnswerHandlerImpl extends AbstractUpdateHandler implements PollAnswerHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PollAnswerHandlerImpl.class);
    private static final String HANDLE_POLL_ANSWER = "Обработка события PollAnswer, объект PollAnswer: {}";
    private static final String HANDLE_POLL_ANSWER_ID = "Обработка события PollAnswer, объект PollAnswer pollId: {}";

    public PollAnswerHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(PollAnswer pollAnswer) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(HANDLE_POLL_ANSWER, pollAnswer);
        } else {
            LOGGER.info(HANDLE_POLL_ANSWER_ID, pollAnswer.getPollId());
        }

        User user = getUser(pollAnswer.getUser());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handlePollAnswer(currentState, user, pollAnswer);
    }
}
