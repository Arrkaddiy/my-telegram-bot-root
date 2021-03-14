package ru.home.telegram.state.authorizephone;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.db.entity.User;

@Component
@RequiredArgsConstructor
@Qualifier(value = "authorizePhoneState")
public class AuthorizePhoneStateImp implements AuthorizePhoneState {

    @Override
    public BotApiMethod<?> handleMessage(User user, Message message) {
        return null;
    }
}
