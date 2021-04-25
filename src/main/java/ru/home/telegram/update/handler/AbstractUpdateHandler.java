package ru.home.telegram.update.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.constant.UpdateContent;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractUpdateHandler<T extends BotApiObject> {
    private static final String SEARCH_USER =
            "Поиск пользователя в базе данных Telegram User: {}";
    private static final String CREATE_NEW_USER =
            "Создаем нового пользователя под данным Telegram User: {}";
    private static final String GET_USER = "" +
            "Получен пользователь User: {}";
    private static final String SAVE_USER_CURRENT_STATE =
            "Сохранение текущей стадии пользователя. State: {}, User: {}";
    private static final String STATE_ROTE =
            "Маршрутизация обработчика по состоянию пользователя. Content: {}, User: {}";

    private final UserService userService;
    private final StateFacade stateFacade;

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

    protected BotApiMethod<?> stateRoute(UpdateContent updateContent, User user, T t) {
        if (log.isDebugEnabled()) {
            log.debug(STATE_ROTE, updateContent, user);
        } else {
            log.info(STATE_ROTE, updateContent, user.getId());
        }

        return stateFacade.route(updateContent, user, t);
    }

    protected void saveUserCurrentState(BotStateType currentState, User user) {
        if (log.isDebugEnabled()) {
            log.debug(SAVE_USER_CURRENT_STATE, currentState, user);
        } else {
            log.info(SAVE_USER_CURRENT_STATE, currentState, user.getId());
        }

        user.setCurrentState(currentState);
        userService.save(user);
    }
}
