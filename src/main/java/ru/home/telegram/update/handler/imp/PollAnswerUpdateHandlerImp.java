package ru.home.telegram.update.handler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import ru.home.telegram.update.handler.AbstractUpdateHandler;
import ru.home.telegram.update.handler.intf.PollAnswerUpdateHandler;

@Component
@Qualifier(value = "pollAnswerUpdateHandler")
public class PollAnswerUpdateHandlerImp extends AbstractUpdateHandler implements PollAnswerUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PollAnswerUpdateHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(PollAnswer pollAnswer) {
        LOGGER.info("Обработка события PollAnswer, объект PollAnswer: {}", pollAnswer);
        return null;
    }
}
