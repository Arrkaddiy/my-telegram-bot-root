package ru.home.telegram.state.start;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;

@Component
@Qualifier(value = "startState")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class StartStateImp implements StartState {

    @Override
    public BotApiMethod<?> handleMessage(User user, Message message) {
        SendMessage errorMessage = new SendMessage();
        errorMessage.setChatId(String.valueOf(user.getTelegramId()));
        errorMessage.setText("Ошибка ");
        return errorMessage;
    }

    @Override
    public SendMessage sendErrorMessage(User user) {
        SendMessage errorMessage = new SendMessage();
        errorMessage.setChatId(String.valueOf(user.getTelegramId()));
        errorMessage.setText("Ошибка обработки");
        return errorMessage;
    }

}
