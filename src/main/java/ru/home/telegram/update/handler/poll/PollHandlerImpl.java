package ru.home.telegram.update.handler.poll;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;

@Slf4j
public class PollHandlerImpl implements PollHandler {
    private static final String HANDLE_POLL =
            "Обработка события Poll, объект Poll: {}";
    private static final String HANDLE_POLL_ID =
            "Обработка события Poll, объект Poll Id: {}";

    @Override
    public BotApiMethod<?> handle(Poll poll) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_POLL, poll);
        } else {
            log.info(HANDLE_POLL_ID, poll.getId());
        }

        return null;
    }
}
