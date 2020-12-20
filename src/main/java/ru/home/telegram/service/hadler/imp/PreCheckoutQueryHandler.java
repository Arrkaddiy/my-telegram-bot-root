package ru.home.telegram.service.hadler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;
import ru.home.telegram.service.hadler.intf.IWebHookBotHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class PreCheckoutQueryHandler implements IWebHookBotHandler<PreCheckoutQuery> {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(PreCheckoutQueryHandler.class);

    @Override
    public void handle(PreCheckoutQuery preCheckoutQuery, TelegramWebhookBot telegramWebhookBot) {
        LOGGER.info("Обработка события PreCheckoutQuery, объект PreCheckoutQuery: {}", preCheckoutQuery);
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("PreCheckoutQueryHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("PreCheckoutQueryHandler destroy");
    }
}
