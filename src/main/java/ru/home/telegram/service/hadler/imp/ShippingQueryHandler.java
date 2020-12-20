package ru.home.telegram.service.hadler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;
import ru.home.telegram.service.hadler.intf.IWebHookBotHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ShippingQueryHandler implements IWebHookBotHandler<ShippingQuery> {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingQueryHandler.class);

    @Override
    public void handle(ShippingQuery shippingQuery, TelegramWebhookBot telegramWebhookBot) {
        LOGGER.info("Обработка события ShippingQuery, объект ShippingQuery: {}", shippingQuery);
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("ShippingQueryHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("ShippingQueryHandler destroy");
    }
}
