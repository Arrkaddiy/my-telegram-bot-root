package ru.home.telegram.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.db.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    /**
     * Сохранение объекта {@link User}
     *
     * @param user - Пользователь {@link User}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(User user) {
        if (user != null) {
            LOGGER.info("Сохранение пользователя User: {}", user);

            user.setLastUpdate(LocalDateTime.now());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Объект сохранения не может быть null!");
        }
    }

    /**
     * Удаление объекта {@link User}
     *
     * @param user - Пользователь {@link User}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(User user) {
        if (user != null) {
            LOGGER.info("Удаление пользователя User Id: {}", user.getId());

            userRepository.delete(user);
        } else {
            throw new IllegalArgumentException("Объект удаления не может быть null!");
        }
    }

    /**
     * Получение записи {@link User} из БД
     *
     * @param id - Id записи
     * @return Объект {@link User}
     */
    public User getUserById(Long id) {
        LOGGER.info("Поиск пользователя по ID: {}", id);

        return userRepository.findById(id).orElse(null);
    }

    /**
     * Получение записи {@link User} из БД
     *
     * @param id - Id записи
     * @return Объект {@link User}
     */
    public User getUserByTelegramId(Integer id) {
        LOGGER.info("Поиск пользователя по Telegram ID: {}", id);

        return userRepository.findByTelegramId(id).orElse(null);
    }
}
