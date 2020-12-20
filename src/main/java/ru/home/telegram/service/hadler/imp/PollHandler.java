package ru.home.telegram.service.hadler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import ru.home.telegram.service.hadler.intf.IWebHookBotHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class PollHandler implements IWebHookBotHandler<Poll> {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(PollHandler.class);

    @Override
    public void handle(Poll poll, TelegramWebhookBot telegramWebhookBot) {
        LOGGER.info("Обработка события Poll, объект Poll: {}", poll);
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
