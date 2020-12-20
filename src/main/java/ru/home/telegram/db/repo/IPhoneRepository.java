package ru.home.telegram.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.telegram.db.entity.Phone;
import ru.home.telegram.db.entity.User;

import java.util.List;

public interface IPhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findAllByUser(Long id);

    List<Phone> findAllByUser(User user);
}
