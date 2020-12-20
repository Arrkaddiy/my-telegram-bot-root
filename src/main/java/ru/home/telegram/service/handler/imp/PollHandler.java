package ru.home.telegram.service.handler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import ru.home.telegram.service.handler.intf.IPollHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class PollHandler implements IPollHandler {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(PollHandler.class);

    @Override
    public BotApiMethod<?> handle(Poll poll) {
        LOGGER.info("Обработка события Poll, объект Poll: {}", poll);
        return null;
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("PollHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("PollHandler destroy");
    }
}
