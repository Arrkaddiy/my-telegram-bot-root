package ru.home.telegram.service.hadler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import ru.home.telegram.service.hadler.intf.IPollAnswerHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class PollAnswerHandler implements IPollAnswerHandler {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(PollAnswerHandler.class);

    @Override
    public BotApiMethod<?> handle(PollAnswer pollAnswer) {
        LOGGER.info("Обработка события PollAnswer, объект PollAnswer: {}", pollAnswer);
        return null;
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("PollAnswerHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("PollAnswerHandler destroy");
    }
}
