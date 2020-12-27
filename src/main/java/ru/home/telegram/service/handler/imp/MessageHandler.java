package ru.home.telegram.service.handler.imp;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.exception.BotMessageHandlerException;
import ru.home.telegram.service.facade.intf.IMessageFacade;
import ru.home.telegram.service.handler.intf.IMessageHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@AllArgsConstructor
public class MessageHandler implements IMessageHandler {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandler.class);

    private IMessageFacade messageFacade;

    @Override
    public BotApiMethod<?> handle(Message message) {
        LOGGER.info("Обработка события Message, объект Message: {}", message);
        try {
            return messageFacade.route(message);
        } catch (BotMessageHandlerException mhe) {
            LOGGER.error("В ходе обработки события Message возникла ошибка {}", mhe.getMessage());
            mhe.printStackTrace();
            return sendError(message);
        }
    }

    private SendMessage sendError(Message message) {
        String chatId = String.valueOf(message.getChatId());
        String text = "Ошибка обработки запроса";
        return new SendMessage(chatId, text);
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("MessageHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("MessageHandler destroy");
    }
}
