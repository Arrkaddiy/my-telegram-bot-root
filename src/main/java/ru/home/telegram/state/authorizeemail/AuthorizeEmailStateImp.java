package ru.home.telegram.state.authorizeemail;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;

@Component
@Qualifier(value = "authorizeEmailState")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class AuthorizeEmailStateImp implements AuthorizeEmailState {

    @Override
    public BotApiMethod<?> handleMessage(User user, Message message) {
        return null;
    }

    @Override
    public SendMessage sendErrorMessage(User user) {
        return null;
    }
}
