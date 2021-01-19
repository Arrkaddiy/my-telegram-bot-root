package ru.home.telegram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.telegram.db.entity.Authorization;
import ru.home.telegram.db.entity.User;

import java.util.Optional;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {

    Optional<Authorization> findByUser(User user);
}
