package ru.home.telegram.service.hadler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.service.hadler.intf.IWebHookBotHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ChannelPostHandler implements IWebHookBotHandler<Message> {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelPostHandler.class);

    @Override
    public void handle(Message message, TelegramWebhookBot telegramWebhookBot) {
        LOGGER.info("Обработка события ChannelPost, объект Message: {}", message);
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("ChannelPostHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("ChannelPostHandler destroy");
    }
}
