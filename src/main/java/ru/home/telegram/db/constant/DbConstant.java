package ru.home.telegram.db.constant;

public final class DbConstant {
    /**
     * Схема в базе данных Postgres
     */
    public static final String DB_SCHEMA_PUBLIC = "public";
    /**
     * Наименование таблицы пользователей
     */
    public static final String DB_TABLE_USER = "u_user";
    /**
     * Наименование таблицы телефонов
     */
    public static final String DB_TABLE_PHONE = "u_phone";
    /**
     * Наименование таблицы почты
     */
    public static final String DB_TABLE_EMAIL = "u_email";

    private DbConstant() {
        throw new IllegalStateException("This is utility class");
    }
}
