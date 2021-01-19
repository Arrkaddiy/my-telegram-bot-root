package ru.home.telegram.service;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.home.telegram.db.entity.Authorization;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.repository.AuthorizationRepository;

import java.time.LocalDateTime;

@Service
public class AuthorizationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationService.class);
    @Setter(onMethod_ = {@Autowired})
    private AuthorizationRepository authorizationRepository;

    /**
     * Сохранение объекта {@link Authorization}
     *
     * @param authorization - Данные авторизации {@link Authorization}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Authorization authorization) {
        if (authorization != null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Сохранение данных Authorization: {}", authorization);
            }

            authorization.setLastUpdate(LocalDateTime.now());
            authorizationRepository.save(authorization);
        } else {
            throw new IllegalArgumentException("Объект сохранения не может быть null!");
        }
    }

    /**
     * Получение записи {@link Authorization} из БД
     *
     * @param user - Пользователь
     * @return Объект {@link Authorization}
     */
    public Authorization getAuthorizationByUser(User user) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Поиск данных по User: {}", user.getId());
        }

        return authorizationRepository.findByUser(user).orElse(null);
    }
}