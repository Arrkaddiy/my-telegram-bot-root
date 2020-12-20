package ru.home.telegram.service.hadler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import ru.home.telegram.service.hadler.intf.IChosenInlineQueryHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ChosenInlineQueryHandler implements IChosenInlineQueryHandler {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(ChosenInlineQueryHandler.class);

    @Override
    public BotApiMethod<?> handle(ChosenInlineQuery chosenInlineQuery) {
        LOGGER.info("Обработка события ChosenInlineQuery, объект ChosenInlineQuery: {}", chosenInlineQuery);
        return null;
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("ChosenInlineQueryHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("ChosenInlineQueryHandler destroy");
    }
}
