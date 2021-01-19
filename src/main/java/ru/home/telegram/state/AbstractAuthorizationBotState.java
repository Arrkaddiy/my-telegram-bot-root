package ru.home.telegram.state;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.home.telegram.db.entity.Authorization;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.AuthorizationService;

public abstract class AbstractAuthorizationBotState extends AbstractBotState {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAuthorizationBotState.class);
    protected static final String AUTHORIZATION_INIT_ERROR = "Необходим ввод в текстовом формате, используя цифры и буквы. " +
            "Попробуй еще раз!";
    @Setter(onMethod_ = {@Autowired})
    protected AuthorizationService authorizationService;


    protected void setAuthorizeEmail(User user, String email) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Сохранение Email в записи авторизации Email: {}, User: {}, ", email, user);
        }

        Authorization authorization = getAuthorization(user);
        authorization.setEmail(email);
        authorizationService.save(authorization);
    }

    protected Authorization getAuthorization(User user) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Поиск записи авторизации в базе данных для User: {}", user);
        }

        Authorization authorization = authorizationService.getAuthorizationByUser(user);

        if (authorization == null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Создаем новую запись авторизации для User: {}", user);
            }

            authorization = new Authorization(user);
            authorizationService.save(authorization);
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Получена запись авторизации Authorization: {}", authorization);
        }

        return authorization;
    }
}
