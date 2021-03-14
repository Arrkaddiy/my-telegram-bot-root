package ru.home.telegram.state.start;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;

@Component
@RequiredArgsConstructor
@Qualifier(value = "startState")
public class StartStateImp implements StartState {

    @Override
    public BotApiMethod<?> handleMessage(User user, Message message) {
        SendMessage errorMessage = new SendMessage();
        errorMessage.setChatId(String.valueOf(user.getTelegramId()));
        errorMessage.setText("Ошибка ");
        return errorMessage;
    }

}
