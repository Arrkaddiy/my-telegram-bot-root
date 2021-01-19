package ru.home.telegram.state.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.constant.BotStateType;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.state.AbstractAuthorizationBotState;
import ru.home.telegram.state.intf.AuthorizationEmailBotState;

@Component
@Qualifier(value = "authorizationEmailBotState")
public class AuthorizationEmailBotBotStateImp extends AbstractAuthorizationBotState implements AuthorizationEmailBotState {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationEmailBotBotStateImp.class);

    @Override
    public BotApiMethod<?> execute(User user, Message message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Выполнение метода init User: {}, Message: {}", user, message);
        }

        String chatId = String.valueOf(message.getChatId());
        setCurrentState(BotStateType.AUTHORIZATION_NAME, user);
        return new SendMessage(chatId, "Теперь ФИО");
    }

}
