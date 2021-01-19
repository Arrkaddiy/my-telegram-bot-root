package ru.home.telegram.state;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.home.telegram.constant.BotStateType;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;

public abstract class AbstractBotState {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBotState.class);
    @Setter(onMethod_ = {@Autowired})
    protected UserService userService;

    protected void setCurrentState(BotStateType state, User user) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Сохранение текущей стадии пользователя State: {}, User: {}", state, user);
        }

        user.setCurrentState(state);
        userService.save(user);
    }
}
