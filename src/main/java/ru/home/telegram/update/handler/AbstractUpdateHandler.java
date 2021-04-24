package ru.home.telegram.update.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.facade.StateFacade;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractUpdateHandler {
    private static final String SEARCH_USER = "Поиск пользователя в базе данных Telegram User: {}";
    private static final String CREATE_NEW_USER = "Создаем нового пользователя под данным Telegram User: {}";
    private static final String GET_USER = "Получен пользователь User: {}";

    private final UserService userService;
    protected final StateFacade stateFacade;

    protected User getUser(org.telegram.telegrambots.meta.api.objects.User telegramUser) {
        Integer telegramUserId = telegramUser.getId();
        if (log.isDebugEnabled()) {
            log.debug(SEARCH_USER, telegramUser);
        } else {
            log.info(SEARCH_USER, telegramUserId);
        }

        User user = userService.getUserByTelegramId(telegramUserId);
        if (user == null) {
            if (log.isDebugEnabled()) {
                log.debug(CREATE_NEW_USER, telegramUser);
            } else {
                log.info(CREATE_NEW_USER, telegramUserId);
            }

            String userName = telegramUser.getUserName();
            String firstName = telegramUser.getFirstName();
            String lastName = telegramUser.getLastName();
            Boolean isBot = telegramUser.getIsBot();
            String languageCode = telegramUser.getLanguageCode();
            Boolean canJoinGroups = telegramUser.getCanJoinGroups();
            Boolean canReadAllGroupMessages = telegramUser.getCanReadAllGroupMessages();
            Boolean supportInlineQueries = telegramUser.getSupportInlineQueries();
            user = new User(
                    telegramUserId,
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

        if (log.isDebugEnabled()) {
            log.debug(GET_USER, user);
        } else {
            log.info(GET_USER, user.getId());
        }
        return user;
    }
}
