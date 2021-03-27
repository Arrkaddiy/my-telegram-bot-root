package ru.home.telegram.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.db.repository.UserRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    /**
     * Сохранение объекта {@link User}
     *
     * @param user - Пользователь {@link User}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(User user) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Сохранение пользователя User: {}", user);
        } else {
            LOGGER.info("Сохранение пользователя User Id {}", user.getId());
        }

        user.setUpdatable(LocalDateTime.now());
        userRepository.save(user);
    }

    /**
     * Удаление объекта {@link User}
     *
     * @param user - Пользователь {@link User}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(User user) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Удаление пользователя User: {}", user);
        } else {
            LOGGER.info("Удаление пользователя User Id: {}", user.getId());
        }

        userRepository.delete(user);
    }

    /**
     * Получение записи {@link User} из БД
     *
     * @param id - Id записи
     * @return Объект {@link User}
     */
    public User getUserById(Long id) {
        LOGGER.info("Поиск пользователя по Id: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Получение записи {@link User} из БД
     *
     * @param id - Id записи
     * @return Объект {@link User}
     */
    public User getUserByTelegramId(Integer id) {
        LOGGER.info("Поиск пользователя по Telegram Id: {}", id);
        return userRepository.findByTelegramId(id).orElse(null);
    }
}
