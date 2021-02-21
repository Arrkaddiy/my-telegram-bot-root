package ru.home.telegram.update.handler.poll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;

@Component
@Qualifier(value = "pollUpdateHandler")
public class PollUpdateHandlerImp implements PollUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PollUpdateHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(Poll poll) {
        LOGGER.info("Обработка события Poll, объект Poll: {}", poll);
        return null;
    }
}
