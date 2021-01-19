package ru.home.telegram.state.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.home.telegram.constant.BotStateType;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.state.AbstractBotState;
import ru.home.telegram.state.intf.StartBotState;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier(value = "startState")
public class StartBotStateImp extends AbstractBotState implements StartBotState {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartBotStateImp.class);
    private static final String START_INIT = "Привет, коллега! Чтобы лучше узнать о нашем коллективе, тебе поможет " +
            "этот бот! Чтобы предоставить полную информация, пожалуйста, представься! Для начала введи свой номер " +
            "телефона";

    @Override
    public BotApiMethod<?> execute(User user, Message message) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Выполнение метода init User: {}, Message: {}", user, message);
        }

        String chatId = String.valueOf(message.getChatId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(START_INIT);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(getReplyKeyBoardMarkup());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Подготовленно ответное сообщение SendMessage: {}", sendMessage);
        }

        setCurrentState(BotStateType.AUTHORIZATION_PHONE, user);
        return sendMessage;
    }

    private ReplyKeyboardMarkup getReplyKeyBoardMarkup() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Подготовка клавиатуры для ответного сообщения");
        }

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(Boolean.TRUE);
        replyKeyboardMarkup.setResizeKeyboard(Boolean.TRUE);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setRequestContact(Boolean.TRUE);
        keyboardButton.setText("Отправить номер");

        keyboardRow.add(keyboardButton);
        keyboardRows.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }
}
