package ru.home.telegram.state.constant;

public class StateMessageText {

    public static final String START_INIT = "Привет, коллега! Чтобы лучше узнать о нашем коллективе, тебе поможет " +
            "этот бот! Чтобы предоставить полную информация, пожалуйста, представься! Для начала введи свой номер " +
            "телефона";

    public static final String AUTHORIZATION_INIT_ERROR = "Необходим ввод в текстовом формате, используя цифры и буквы" +
            "Попробуй еще раз!";

    private StateMessageText() {
        throw new IllegalStateException("This is utility class");
    }
}
