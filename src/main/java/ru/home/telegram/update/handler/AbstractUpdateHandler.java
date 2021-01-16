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
    protected UserService userService;

    /**
     * Поиск пользователя в базе данных по уникальному Id
     * В случае его отсутсвия - создание нового пользователя в базе
     *
     * @param telegramUser - Пользователь из входящего запроса {@link org.telegram.telegrambots.meta.api.objects.User}
     * @return Объект класса {@link User}
     */
    protected User getUser(org.telegram.telegrambots.meta.api.objects.User telegramUser) {
        Integer telegramUserId = telegramUser.getId();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Поиск пользователя в базе данных по Telegram Id: {}", telegramUserId);
        }

        User user = userService.getUserByTelegramId(telegramUserId);

        if (user == null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Создаем нового пользователя под данным Telegram Id: {}", telegramUserId);
            }
            String firstName = telegramUser.getFirstName();
            String lastName = telegramUser.getLastName();
            String userName = telegramUser.getUserName();
            user = new User(telegramUserId, firstName, lastName, userName);
            userService.save(user);
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Получен пользователь User: {}", user);
        }

        return user;
    }
}
