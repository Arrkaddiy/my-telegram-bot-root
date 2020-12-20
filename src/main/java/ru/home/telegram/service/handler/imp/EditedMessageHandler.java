package ru.home.telegram.service.handler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.service.handler.intf.IEditedMessageHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class EditedMessageHandler implements IEditedMessageHandler {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(EditedMessageHandler.class);

    @Override
    public BotApiMethod<?> handle(Message message) {
        LOGGER.info("Обработка события EditedMessage, объект Message: {}", message);
        return null;
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("EditedMessageHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("EditedMessageHandler destroy");
    }
}
