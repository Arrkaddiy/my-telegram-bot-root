package ru.home.telegram.update.handler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.constant.BotCommand;
import ru.home.telegram.constant.MessageEntityType;
import ru.home.telegram.constant.UserState;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.update.handler.AbstractUpdateHandler;
import ru.home.telegram.update.handler.intf.MessageUpdateHandler;

@Component
@Qualifier(value = "messageUpdateHandler")
public class MessageUpdateHandlerImp extends AbstractUpdateHandler implements MessageUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUpdateHandlerImp.class);

    /**
     * Обработка входящего запроса типа Message
     *
     * @param message - Входящий запрос типа {@link Message}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> handle(Message message) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Обработка события Message, объект Message: {}", message);
        }

        User user = getUser(message.getFrom());

        if (isRebaseBot(message)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Перезапуск бота для пользователя User: {}", user);
            }

            deleteUser(user);
            user = getUser(message.getFrom());
        }

        UserState state = user.getCurrentState();
        return new SendMessage(String.valueOf(message.getChatId()), message.getText() + "_OK_" + state);
    }

    private boolean isRebaseBot(Message message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Проверка необходимости перезапустить бота");
        }

        boolean isRebaseBot = false;
        if (message.hasEntities()) {
            isRebaseBot = message.getEntities()
                    .stream()
                    .filter(messageEntity -> MessageEntityType.BOT_COMMAND
                            .equals(MessageEntityType.getBotCommandByType(messageEntity.getType())))
                    .anyMatch(messageEntity -> BotCommand.START
                            .equals(BotCommand.getCommandByText(messageEntity.getText())));
        }

        if (message.hasText() && !isRebaseBot) {
            isRebaseBot = BotCommand.START.getCommand().equalsIgnoreCase(message.getText());
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Необходимость перезапуска бота - {}", isRebaseBot);
        }

        return isRebaseBot;
    }

    private void deleteUser(User user) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Удаление данных пользователя User: {}", user);
        }

        userService.delete(user);
    }
}
