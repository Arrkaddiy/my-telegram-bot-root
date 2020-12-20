package ru.home.telegram.db.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.home.telegram.db.constant.DbConstant;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(of = {"id", "number"})
@ToString(of = {"id", "user", "number", "type", "active"})
@Table(name = DbConstant.DB_TABLE_PHONE, schema = DbConstant.DB_SCHEMA_PUBLIC)
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "CREATION", nullable = false, updatable = false)
    private LocalDateTime creation;
    @Column(name = "LAST_UPDATE", nullable = false)
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private User user;
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String number;
    @Column(name = "TYPE", nullable = false)
    private String type;
    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    public Phone() {
        //Null Constructor
    }

    public Phone(User user, String number) {
        this(user, number, "Type", true);
    }

    public Phone(User user, String number, String type, Boolean active) {
        this.user = user;
        this.number = number;
        this.type = type;
        this.active = active;
    }
}
