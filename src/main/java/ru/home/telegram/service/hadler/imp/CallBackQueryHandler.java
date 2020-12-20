package ru.home.telegram.service.hadler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.home.telegram.service.hadler.intf.ICallBackQueryHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class CallBackQueryHandler implements ICallBackQueryHandler {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(CallBackQueryHandler.class);

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        LOGGER.info("Обработка события CallbackQuery, объект CallbackQuery: {}", callbackQuery);
        return null;
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("CallBackQueryHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("CallBackQueryHandler destroy");
    }
}
