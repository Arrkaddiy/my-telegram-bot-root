package ru.home.telegram.state.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import ru.home.telegram.constant.BotStateType;
import ru.home.telegram.constant.MessageEntityType;
import ru.home.telegram.db.entity.Authorization;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRuntimeException;
import ru.home.telegram.state.AbstractAuthorizationBotState;
import ru.home.telegram.state.intf.AuthorizationPhoneBotState;

import java.util.List;

@Component
@Qualifier(value = "authorizationPhoneBotState")
public class AuthorizationPhoneBotStateImp extends AbstractAuthorizationBotState implements AuthorizationPhoneBotState {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationPhoneBotStateImp.class);

    @Override
    public BotApiMethod<?> execute(User user, Message message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Выполнение метода init User: {}, Message: {}", user, message);
        }

        String chatId = String.valueOf(message.getChatId());

        if (message.hasContact()) {
            return contactHandler(user, chatId, message.getContact());
        }

        if (message.hasEntities()) {
            return entityHandler(user, chatId, message.getEntities());
        }

        return getErrorMessage(chatId);
    }

    private BotApiMethod<?> contactHandler(User user, String chatId, Contact contact) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Получен объект типа Contact: {}", contact);
        }

        if (!checkContact(user, contact)) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Получен некорректный объект типа Contact: {}", contact);
            }

            return getErrorMessage(chatId);
        }

        String phoneNumber = contact.getPhoneNumber();
        setAuthorizePhone(user, phoneNumber);

        return getSuccessMessage(user, chatId);
    }

    private BotApiMethod<?> entityHandler(User user, String chatId, List<MessageEntity> messageEntityList) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Получен объект типа MessageEntity");
        }

        if (!checkPhoneEntity(messageEntityList)) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Получен некорректный объект типа MessageEntity!");
            }

            return getErrorMessage(chatId);
        }

        String phoneNumber = messageEntityList.stream()
                .filter(messageEntity -> MessageEntityType.PHONE_NUMBER
                        .equals(MessageEntityType.getMessageEntityTypeByMessageEntity(messageEntity)))
                .findFirst()
                .orElseThrow(BotRuntimeException::new)
                .getText();
        setAuthorizePhone(user, phoneNumber);

        return getSuccessMessage(user, chatId);
    }

    private void setAuthorizePhone(User user, String phoneNumber) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Сохранение телефона в записи авторизации Phone: {}, User: {}, ", phoneNumber, user);
        }

        Authorization authorization = getAuthorization(user);
        authorization.setPhone(phoneNumber);
        authorizationService.save(authorization);
    }

    private boolean checkContact(User user, Contact contact) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Сверка данных текущего пользователя с присланым контактом User: {}, Contact: {}", user, contact);
        }
        return user.getFirstName().equals(contact.getFirstName()) && user.getLastName().equals(contact.getLastName());
    }

    private boolean checkPhoneEntity(List<MessageEntity> messageEntityList) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Поиск вложений телефонов и подсчет количетсва");
        }

        return messageEntityList.stream()
                .filter(messageEntity -> MessageEntityType.PHONE_NUMBER
                        .equals(MessageEntityType.getMessageEntityTypeByMessageEntity(messageEntity)))
                .count() == 1L;
    }

    private SendMessage getSuccessMessage(User user, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Введи email");
        sendMessage.setChatId(chatId);

        setCurrentState(BotStateType.AUTHORIZATION_EMAIL, user);
        return sendMessage;
    }

    private SendMessage getErrorMessage(String chatId) {
        SendMessage errorMessage = new SendMessage();
        errorMessage.setChatId(chatId);
        errorMessage.setText(AUTHORIZATION_INIT_ERROR);

        return errorMessage;
    }
}
