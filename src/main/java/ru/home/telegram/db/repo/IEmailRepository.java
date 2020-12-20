package ru.home.telegram.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.telegram.db.entity.Email;
import ru.home.telegram.db.entity.User;

import java.util.List;

public interface IEmailRepository extends JpaRepository<Email, Long> {

    List<Email> findAllByUser(Long id);

    List<Email> findAllByUser(User user);
}
