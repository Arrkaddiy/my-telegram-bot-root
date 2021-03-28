package ru.home.telegram.update.handler.poll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;

public class PollHandlerImpl implements PollHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PollHandlerImpl.class);
    private static final String HANDLE_POLL = "Обработка события Poll, объект Poll: {}";
    private static final String HANDLE_POLL_ID = "Обработка события Poll, объект Poll Id: {}";

    @Override
    public BotApiMethod<?> handle(Poll poll) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(HANDLE_POLL, poll);
        } else {
            LOGGER.info(HANDLE_POLL_ID, poll.getId());
        }

        return null;
    }
}
