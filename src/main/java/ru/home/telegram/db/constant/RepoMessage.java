package ru.home.telegram.db.constant;

public final class RepoMessage {

    public static final String SAVE = "Сохранение объекта {}: {}";

    public static final String FIND_BY_ID = "Получение записи {} по Id: {}";
    public static final String FIND_BY_TELEGRAM_ID = "Получение записи {} по Telegram Id: {}";
    public static final String FIND_ALL_BY_ID = "Получение коллекции записей {} по Id: {}";

    public static final String ERROR_OBJECT_NULL = "Не передан объект {}!";

    private RepoMessage() {
        throw new IllegalStateException("This is utility class");
    }
}
