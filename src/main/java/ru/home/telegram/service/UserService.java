package ru.home.telegram.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.home.telegram.db.constant.RepoMessage;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.db.repo.IUserRepository;
import ru.home.telegram.exception.RepositoryExecuteException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    //Наименование DAO
    private static final String DAO_NAME = "User";
    //Репозиторий запросов в БД
    private IUserRepository repository;

    /**
     * Сохранение объекта {@link User}
     *
     * @param user - Пользователь {@link User}
     * @return Объект {@link User}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User save(User user) {
        if (Objects.nonNull(user)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(RepoMessage.SAVE, DAO_NAME, user);
            }

            user.setLastUpdate(LocalDateTime.now());
            return repository.save(user);
        } else {
            throw new RepositoryExecuteException(RepoMessage.ERROR_OBJECT_NULL);
        }
    }

    /**
     * Удаление объекта {@link User}
     *
     * @param user - Пользователь {@link User}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(User user) {
        if (Objects.nonNull(user)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(RepoMessage.SAVE, DAO_NAME, user);
            }

            repository.delete(user);
        } else {
            throw new RepositoryExecuteException(RepoMessage.ERROR_OBJECT_NULL);
        }
    }

    /**
     * Получение записи {@link User} из БД
     *
     * @param id - Id записи
     * @return Объект {@link User}
     */
    public User getUserById(Long id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(RepoMessage.FIND_BY_ID, DAO_NAME, id);
        }

        return repository.findById(id).orElse(null);
    }

    /**
     * Получение записи {@link User} из БД
     *
     * @param id - Id записи
     * @return Объект {@link User}
     */
    public User getUserByTelegramId(Integer id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(RepoMessage.FIND_BY_TELEGRAM_ID, DAO_NAME, id);
        }

        return repository.findByTelegramId(id).orElse(null);
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("UserService initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("UserService destroy");
    }
}
