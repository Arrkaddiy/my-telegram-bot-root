package ru.home.telegram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.db.repository.UserRepository;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String SAVE_USER = "Сохранение пользователя User: {}";
    private static final String DELETE_USER = "Удаление пользователя User: {}";
    private static final String GET_USER_BY_ID = "Поиск пользователя по Id: {}";
    private static final String GET_USER_BY_TELEGRAM_ID = "Поиск пользователя по Telegram Id: {}";

    private final UserRepository userRepository;

    /**
     * Сохранение объекта {@link User}
     *
     * @param user - Пользователь {@link User}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(User user) {
        if (log.isDebugEnabled()) {
            log.debug(SAVE_USER, user);
        } else {
            log.info(SAVE_USER, user.getId());
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
        if (log.isDebugEnabled()) {
            log.debug(DELETE_USER, user);
        } else {
            log.info(DELETE_USER, user.getId());
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
        log.info(GET_USER_BY_ID, id);
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Получение записи {@link User} из БД
     *
     * @param id - Id записи
     * @return Объект {@link User}
     */
    public User getUserByTelegramId(Integer id) {
        log.info(GET_USER_BY_TELEGRAM_ID, id);
        return userRepository.findByTelegramId(id).orElse(null);
    }
}
