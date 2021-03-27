package ru.home.telegram.service;

import ru.home.telegram.db.entity.User;

public interface UserService {

    void save(User user);

    void delete(User user);

    User getUserById(Long id);

    User getUserByTelegramId(Integer id);
}
