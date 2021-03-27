package ru.home.telegram.update.handler.poll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;

public class PollHandlerImpl implements PollHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PollHandlerImpl.class);

    @Override
    public BotApiMethod<?> handle(Poll poll) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Обработка события Poll, объект Poll: {}", poll);
        } else {
            LOGGER.info("Обработка события Poll, объект Poll Id: {}", poll.getId());
        }

        return null;
    }
}
