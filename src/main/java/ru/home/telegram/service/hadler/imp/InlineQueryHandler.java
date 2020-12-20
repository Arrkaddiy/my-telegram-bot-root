package ru.home.telegram.service.hadler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import ru.home.telegram.service.hadler.intf.IWebHookBotHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class InlineQueryHandler implements IWebHookBotHandler<InlineQuery> {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(InlineQueryHandler.class);

    @Override
    public void handle(InlineQuery inlineQuery, TelegramWebhookBot telegramWebhookBot) {
        LOGGER.info("Обработка события InlineQuery, объект InlineQuery: {}", inlineQuery);
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("InlineQueryHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("InlineQueryHandler destroy");
    }
}
