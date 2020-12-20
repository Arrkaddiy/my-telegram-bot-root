package ru.home.telegram.db.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.home.telegram.db.constant.DbConstant;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(of = {"id", "address"})
@ToString(of = {"id", "user", "address", "type", "active"})
@Table(name = DbConstant.DB_TABLE_EMAIL, schema = DbConstant.DB_SCHEMA_PUBLIC)
public class Email {
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
    @Column(name = "EMAIL_ADDRESS", nullable = false)
    private String address;
    @Column(name = "TYPE", nullable = false)
    private String type;
    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    public Email() {
        //Null Constructor
    }

    public Email(User user, String address) {
        this(user, address, "Type", true);
    }

    public Email(User user, String address, String type, Boolean active) {
        this.user = user;
        this.address = address;
        this.type = type;
        this.active = active;
    }
}
