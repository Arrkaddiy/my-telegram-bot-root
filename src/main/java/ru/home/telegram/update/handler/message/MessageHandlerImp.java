package ru.home.telegram.update.handler.message;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;

@Component
@Qualifier(value = "messageHandler")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MessageHandlerImp implements MessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerImp.class);
    private UserService userService;

    /**
     * Обработка входящего запроса типа Message
     *
     * @param message - Входящий запрос типа {@link Message}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> handle(Message message) {
        LOGGER.info("Обработка события Message, объект Message: {}", message);
        return null;
    }

    private User getUser(org.telegram.telegrambots.meta.api.objects.User telegramUser) {
        Integer telegramUserId = telegramUser.getId();
        LOGGER.info("Поиск пользователя в базе данных по Telegram Id: {}", telegramUserId);

        User user = userService.getUserByTelegramId(telegramUserId);
        if (user == null) {
            LOGGER.info("Создаем нового пользователя под данным Telegram Id: {}", telegramUserId);
            String firstName = telegramUser.getFirstName();
            String lastName = telegramUser.getLastName();
            String userName = telegramUser.getUserName();
            user = new User(telegramUserId, firstName, lastName, userName);
            userService.save(user);
        }

        LOGGER.info("Получен пользователь User: {}", user);
        return user;
    }
}
