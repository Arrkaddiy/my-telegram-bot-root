package ru.home.telegram.state.start;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.state.AbstractState;

public class StartStateImpl extends AbstractState implements StartState {

    @Override
    public BotApiMethod<?> handleMessage(User user, Message message) {
        SendMessage errorMessage = new SendMessage();
        errorMessage.setChatId(String.valueOf(user.getTelegramId()));
        errorMessage.setText("START");
        return errorMessage;
    }

}
