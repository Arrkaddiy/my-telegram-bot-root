package ru.home.telegram.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.telegram.db.entity.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByTelegramId(Integer id);
}
