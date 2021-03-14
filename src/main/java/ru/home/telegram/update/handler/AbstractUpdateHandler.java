package ru.home.telegram.update.handler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.State;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;

@RequiredArgsConstructor
public abstract class AbstractUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractUpdateHandler.class);

    private final UserService userService;
    private final StateFacade stateFacade;

    protected User getUser(org.telegram.telegrambots.meta.api.objects.User telegramUser) {
        Integer telegramUserId = telegramUser.getId();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Поиск пользователя в базе данных Telegram User: {}", telegramUser);
        } else {
            LOGGER.info("Поиск пользователя в базе данных по Telegram Id: {}", telegramUserId);
        }

        User user = userService.getUserByTelegramId(telegramUserId);
        if (user == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Создаем нового пользователя под данным Telegram User: {}", telegramUser);
            } else {
                LOGGER.info("Создаем нового пользователя под данным Telegram Id: {}", telegramUserId);
            }

            String userName = telegramUser.getUserName();
            String firstName = telegramUser.getFirstName();
            String lastName = telegramUser.getLastName();
            Boolean isBot = telegramUser.getIsBot();
            String languageCode = telegramUser.getLanguageCode();
            Boolean canJoinGroups = telegramUser.getCanJoinGroups();
            Boolean canReadAllGroupMessages = telegramUser.getCanReadAllGroupMessages();
            Boolean supportInlineQueries = telegramUser.getSupportInlineQueries();
            user = new User(telegramUserId,
                    userName,
                    firstName,
                    lastName,
                    isBot,
                    languageCode,
                    canJoinGroups,
                    canReadAllGroupMessages,
                    supportInlineQueries);
            userService.save(user);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Получен пользователь User: {}", user);
        } else {
            LOGGER.info("Получен пользователь User Id: {}", user.getId());
        }
        return user;
    }

    protected State getState(User user) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Получение текущей стадии пользователя User: {}", user);
        } else {
            LOGGER.info("Получение текущей стадии пользователя Telegram Id: {}", user.getTelegramId());
        }
        BotStateType botStateType = user.getCurrentState();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Получено значение текущей стадии пользователя State: {}", botStateType);
        }
        return stateFacade.route(botStateType);
    }

    protected SendMessage getErrorStateMessage(User user) {
        SendMessage errorMessage = new SendMessage();
        errorMessage.setChatId(String.valueOf(user.getTelegramId()));
        errorMessage.setText("");
        return errorMessage;
    }
}
