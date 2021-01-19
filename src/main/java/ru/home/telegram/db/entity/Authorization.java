package ru.home.telegram.db.entity;

import lombok.*;
import ru.home.telegram.db.constant.DbConstant;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Объект хранения данных в БД
 *
 * В нем описывается структура таблицы, колонки и тд
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = DbConstant.DB_TABLE_AUTHORIZATION, schema = DbConstant.DB_SCHEMA_PUBLIC)
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "CREATION", nullable = false, updatable = false)
    private LocalDateTime creation;
    @Column(name = "LAST_UPDATE", nullable = false)
    private LocalDateTime lastUpdate;
    @OneToOne
    private User user;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "PATRONYMIC")
    private String patronymic;
    @Column(name = "COUNT")
    private Integer count;

    public Authorization(User user) {
        this.creation = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
        this.user = user;
        this.count = 0;
    }
}
