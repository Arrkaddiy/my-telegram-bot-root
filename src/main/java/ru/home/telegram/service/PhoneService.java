package ru.home.telegram.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.home.telegram.db.constant.RepoMessage;
import ru.home.telegram.db.entity.Phone;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.db.repo.IPhoneRepository;
import ru.home.telegram.exception.BotRepositoryException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PhoneService {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneService.class);
    //Наименование DAO
    private static final String DAO_NAME = "Phone";
    //Репозиторий запросов в БД
    private IPhoneRepository repository;

    /**
     * Сохранение объекта {@link Phone}
     *
     * @param phone - Телефон {@link Phone}
     * @return Объект {@link Phone}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Phone save(Phone phone) {
        if (Objects.nonNull(phone)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(RepoMessage.SAVE, DAO_NAME, phone);
            }

            phone.setLastUpdate(LocalDateTime.now());
            return repository.save(phone);
        } else {
            throw new BotRepositoryException(RepoMessage.ERROR_OBJECT_NULL);
        }
    }

    /**
     * Удаление объекта {@link Phone}
     *
     * @param phone - Телефон {@link Phone}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Phone phone) {
        if (Objects.nonNull(phone)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(RepoMessage.SAVE, DAO_NAME, phone);
            }

            repository.delete(phone);
        } else {
            throw new BotRepositoryException(RepoMessage.ERROR_OBJECT_NULL);
        }
    }

    /**
     * Получение объекта {@link Phone} из БД
     *
     * @param id - Id записи
     * @return Объект {@link Phone}
     */
    public Phone getPhoneById(Long id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(RepoMessage.FIND_BY_ID, DAO_NAME, id);
        }

        return repository.findById(id).orElse(null);
    }

    /**
     * Получение коллекции объектов {@link Phone} из БД
     *
     * @param id - Id записи
     * @return Коллекция объектов {@link Phone}
     */
    public List<Phone> getPhonesByUser(Long id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(RepoMessage.FIND_ALL_BY_ID, DAO_NAME, id);
        }

        return repository.findAllByUser(id);
    }

    /**
     * Получение коллекции объектов {@link Phone} из БД
     *
     * @param user - Пользователь
     * @return Коллекция объектов {@link Phone}
     */
    public List<Phone> getPhonesByUser(User user) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(RepoMessage.FIND_ALL_BY_ID, DAO_NAME, user);
        }

        return repository.findAllByUser(user);
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("PhoneService initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("PhoneService destroy");
    }
}
