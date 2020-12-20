package ru.home.telegram.bot;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.service.facade.intf.IUpdateFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;

/**
 * Telegram Bot
 */

@Getter
@Component
public class MyTelegramBot extends TelegramWebhookBot {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(MyTelegramBot.class);
    //Переменная хронящее в себе данный из файла настроек по указанному пути
    @Value("${telegram.bot.userName}")
    private String botUsername;
    //Переменная хронящее в себе данный из файла настроек по указанному пути
    @Value("${telegram.bot.token}")
    private String botToken;
    //Переменная хронящее в себе данный из файла настроек по указанному пути
    @Value("${telegram.bot.webHook}")
    private String botPath;

    private final IUpdateFacade updateFacade;

    public MyTelegramBot(IUpdateFacade updateFacade) {
        this.updateFacade = updateFacade;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        LOGGER.info("Получен входящий запрос Update: {}", update);
        if (Objects.nonNull(update)) {
            return updateFacade.handle(update);
        }

        return null;
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("MyTelegramBot initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("MyTelegramBot destroy");
    }
}
