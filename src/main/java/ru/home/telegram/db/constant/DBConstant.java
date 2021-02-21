package ru.home.telegram.db.constant;

public final class DBConstant {
    /**
     * Схема в базе данных Postgres
     */
    public static final String DB_SCHEMA_PUBLIC = "public";
    /**
     * Наименование таблицы пользователей
     */
    public static final String DB_TABLE_USER = "bot_user";
    /**
     * Наименование таблицы записей авторизации
     */
    public static final String DB_TABLE_AUTHORIZATION = "bot_authorization";

    private DBConstant() {
        throw new IllegalStateException("This is utility class");
    }
}