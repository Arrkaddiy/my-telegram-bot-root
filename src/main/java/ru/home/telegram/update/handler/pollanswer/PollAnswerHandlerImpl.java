package ru.home.telegram.update.handler.pollanswer;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class PollAnswerHandlerImpl extends AbstractUpdateHandler implements PollAnswerHandler {
    private static final String HANDLE_POLL_ANSWER = "Обработка события PollAnswer, объект PollAnswer: {}";

    public PollAnswerHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    @Override
    public BotApiMethod<?> handle(PollAnswer pollAnswer) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_POLL_ANSWER, pollAnswer);
        } else {
            log.info(HANDLE_POLL_ANSWER, pollAnswer.getPollId());
        }

        User user = getUser(pollAnswer.getUser());
        BotStateType currentState = user.getCurrentState();

        return stateFacade.handlePollAnswer(currentState, user, pollAnswer);
    }
}
