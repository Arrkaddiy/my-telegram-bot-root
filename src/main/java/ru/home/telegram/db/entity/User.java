package ru.home.telegram.db.entity;

import lombok.*;
import ru.home.telegram.constant.BotStateType;
import ru.home.telegram.db.constant.DBConstant;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Объект хранения данных в БД
 * <p>
 * В нем описывается структура таблицы, колонки и тд
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "telegramId"})
@ToString(of = {"id", "telegramId", "userName", "firstName", "lastName", "currentState"})
@Table(name = DBConstant.DB_TABLE_USER, schema = DBConstant.DB_SCHEMA_PUBLIC)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "CREATION", nullable = false, updatable = false)
    private LocalDateTime creation = LocalDateTime.now();
    @Column(name = "TELEGRAM_ID", nullable = false, unique = true, updatable = false)
    private Integer telegramId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "LEAGUE_AUTHORIZE", nullable = false)
    private Boolean authorize = Boolean.FALSE;
    @Enumerated(EnumType.STRING)
    private BotStateType currentState = BotStateType.START;

    public User(Integer telegramId, String userName, String firstName, String lastName) {
        this.telegramId = telegramId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
