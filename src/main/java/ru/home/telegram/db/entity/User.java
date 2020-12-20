package ru.home.telegram.db.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.home.telegram.db.constant.DbConstant;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Объект хранения данных в БД
 * <p>
 * В нем описывается структура таблицы, колонки и тд
 * </p>
 */

@Data
@Entity
@EqualsAndHashCode(of = {"id", "telegramId"})
@ToString(of = {"id", "telegramId", "userName", "firstName", "lastName"})
@Table(name = DbConstant.DB_TABLE_USER, schema = DbConstant.DB_SCHEMA_PUBLIC)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "CREATION", nullable = false, updatable = false)
    private LocalDateTime creation;
    @Column(name = "LAST_UPDATE", nullable = false)
    private LocalDateTime lastUpdate;
    @Column(name = "TELEGRAM_ID", nullable = false, unique = true, updatable = false)
    private Integer telegramId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "REAL_FIRST_NAME")
    private String realFirstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "REAL_LAST_NAME")
    private String realLastName;
    @Column(name = "REAL_PATRONYMIC")
    private String realPatronymic;
    @Column(name = "LEAGUE_AUTHORIZE", nullable = false)
    private Boolean authorize;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Phone> phones;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Email> emails;

    public User() {
        //Null Constructor
    }

    public User(Integer telegramId, String firstName, String lastName, String userName) {
        this.creation = LocalDateTime.now();
        this.telegramId = telegramId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.authorize = false;
    }
}
