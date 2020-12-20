package ru.home.telegram.service.hadler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import ru.home.telegram.service.hadler.intf.IInlineQueryHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class InlineQueryHandler implements IInlineQueryHandler {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(InlineQueryHandler.class);

    @Override
    public BotApiMethod<?> handle(InlineQuery inlineQuery) {
        LOGGER.info("Обработка события InlineQuery, объект InlineQuery: {}", inlineQuery);
        return null;
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
