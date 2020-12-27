package ru.home.telegram.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.home.telegram.db.constant.RepoMessage;
import ru.home.telegram.db.entity.Email;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.db.repo.IEmailRepository;
import ru.home.telegram.exception.BotRepositoryException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EmailService {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    //Наименование DAO
    private static final String DAO_NAME = "Email";
    //Репозиторий запросов в БД
    private IEmailRepository repository;

    /**
     * Сохранение объекта {@link Email}
     *
     * @param email - Почта {@link Email}
     * @return Объект {@link Email}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Email save(Email email) {
        if (Objects.nonNull(email)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(RepoMessage.SAVE, DAO_NAME, email);
            }

            email.setLastUpdate(LocalDateTime.now());
            return repository.save(email);
        } else {
            throw new BotRepositoryException(RepoMessage.ERROR_OBJECT_NULL);
        }
    }

    /**
     * Удаление объекта {@link Email}
     *
     * @param email - Почта {@link Email}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Email email) {
        if (Objects.nonNull(email)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(RepoMessage.SAVE, DAO_NAME, email);
            }

            repository.delete(email);
        } else {
            throw new BotRepositoryException(RepoMessage.ERROR_OBJECT_NULL);
        }
    }

    /**
     * Получение объекта {@link Email} из БД
     *
     * @param id - Id записи
     * @return Объект {@link Email}
     */
    public Email getEmailById(Long id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(RepoMessage.FIND_BY_ID, DAO_NAME, id);
        }

        return repository.findById(id).orElse(null);
    }

    /**
     * Получение коллекции объектов {@link Email} из БД
     *
     * @param id - Id записи
     * @return Коллекция объектов {@link Email}
     */
    public List<Email> getEmailsByUser(Long id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(RepoMessage.FIND_ALL_BY_ID, DAO_NAME, id);
        }

        return repository.findAllByUser(id);
    }

    /**
     * Получение коллекции объектов {@link Email} из БД
     *
     * @param user - Пользователь
     * @return Коллекция объектов {@link Email}
     */
    public List<Email> getEmailsByUser(User user) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(RepoMessage.FIND_ALL_BY_ID, DAO_NAME, user);
        }

        return repository.findAllByUser(user);
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("EmailService initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("EmailService destroy");
    }
}
