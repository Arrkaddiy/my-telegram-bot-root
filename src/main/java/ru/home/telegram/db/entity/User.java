package ru.home.telegram.db.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.home.telegram.db.constant.DBConstant;
import ru.home.telegram.state.constant.BotStateType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Объект хранения данных в БД
 * <p>
 * В нем описывается структура таблицы, колонки и тд
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "telegramId"})
@Table(name = DBConstant.DB_TABLE_USER, schema = DBConstant.DB_SCHEMA_PUBLIC)
public class User {

    private static final String CREATION_FIELD = "creation";
    private static final String UPDATABLE_FIELD = "updatable";
    private static final String TELEGRAM_ID_FIELD = "telegram_id";
    private static final String USER_NAME_FIELD = "user_name";
    private static final String FIRST_NAME_FIELD = "first_name";
    private static final String LAST_NAME_FIELD = "last_name";
    private static final String IS_BOT_FIELD = "is_bot";
    private static final String LANGUAGE_CODE_FIELD = "language_code";
    private static final String CAN_JOIN_GROUPS_FIELD = "can_join_groups";
    private static final String CAN_READ_ALL_GROUP_MESSAGES_FIELD = "can_read_all_group_messages";
    private static final String SUPPORT_INLINE_QUERIES_FIELD = "supports_inline_queries";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = CREATION_FIELD, nullable = false, updatable = false)
    private LocalDateTime creation = LocalDateTime.now();
    @Column(name = UPDATABLE_FIELD, nullable = false)
    private LocalDateTime updatable = LocalDateTime.now();
    @Column(name = TELEGRAM_ID_FIELD, nullable = false, unique = true, updatable = false)
    private Integer telegramId;
    @Column(name = USER_NAME_FIELD)
    private String userName;
    @Column(name = FIRST_NAME_FIELD)
    private String firstName;
    @Column(name = LAST_NAME_FIELD)
    private String lastName;
    @Column(name = IS_BOT_FIELD, nullable = false)
    private Boolean isBot;
    @Column(name = LANGUAGE_CODE_FIELD)
    private String languageCode;
    @Column(name = CAN_JOIN_GROUPS_FIELD)
    private Boolean canJoinGroups;
    @Column(name = CAN_READ_ALL_GROUP_MESSAGES_FIELD)
    private Boolean canReadAllGroupMessages;
    @Column(name = SUPPORT_INLINE_QUERIES_FIELD)
    private Boolean supportInlineQueries;
    @Enumerated(EnumType.STRING)
    private BotStateType currentState = BotStateType.START;

    public User(Integer telegramId,
                String userName,
                String firstName,
                String lastName,
                Boolean isBot,
                String languageCode,
                Boolean canJoinGroups,
                Boolean canReadAllGroupMessages,
                Boolean supportInlineQueries) {
        this.telegramId = telegramId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isBot = isBot;
        this.languageCode = languageCode;
        this.canJoinGroups = canJoinGroups;
        this.canReadAllGroupMessages = canReadAllGroupMessages;
        this.supportInlineQueries = supportInlineQueries;
    }
}
