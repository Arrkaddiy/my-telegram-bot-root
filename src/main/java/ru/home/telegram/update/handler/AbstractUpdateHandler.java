package ru.home.telegram.update.handler;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;

public abstract class AbstractUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractUpdateHandler.class);
    @Setter(onMethod_ = {@Autowired})
    private UserService userService;

    protected User getUser(org.telegram.telegrambots.meta.api.objects.User telegramUser) {
        Integer telegramUserId = telegramUser.getId();
        LOGGER.info("Поиск пользователя в базе данных по Telegram Id: {}", telegramUserId);

        User user = userService.getUserByTelegramId(telegramUserId);
        if (user == null) {
            LOGGER.info("Создаем нового пользователя под данным Telegram Id: {}", telegramUserId);
            String userName = telegramUser.getUserName();
            String firstName = telegramUser.getFirstName();
            String lastName = telegramUser.getLastName();
            user = new User(telegramUserId, userName, firstName, lastName);
            userService.save(user);
        }

        LOGGER.info("Получен пользователь User: {}", user);
        return user;
    }
}
