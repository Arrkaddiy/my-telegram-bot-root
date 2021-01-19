package ru.home.telegram.update.handler.imp;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.constant.BotCommand;
import ru.home.telegram.constant.BotStateType;
import ru.home.telegram.constant.MessageEntityType;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.state.scenario.StateScenarioRouter;
import ru.home.telegram.update.handler.AbstractUpdateHandler;
import ru.home.telegram.update.handler.intf.MessageUpdateHandler;

@Component
@Qualifier(value = "messageUpdateHandler")
public class MessageUpdateHandlerImp extends AbstractUpdateHandler implements MessageUpdateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUpdateHandlerImp.class);
    @Setter(onMethod_ = {@Autowired})
    private StateScenarioRouter stateScenarioRouter;

    /**
     * Обработка входящего запроса типа Message
     *
     * @param message - Входящий запрос типа {@link Message}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> handle(Message message) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Обработка события Message, объект Message: {}", message);
        }

        User user = getUser(message.getFrom());
        BotStateType state = user.getCurrentState();

        if (isRebaseBot(message) && !BotStateType.START.equals(state)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Перезапуск бота для пользователя User: {}", user);
            }

            deleteUser(user);
            user = getUser(message.getFrom());
        }

        return stateScenarioRouter.route(state, user, message);
    }

    private boolean isRebaseBot(Message message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Проверка необходимости перезапустить бота Message: {}", message);
        }

        if (message.hasEntities()) {
            return message.getEntities()
                    .stream()
                    .filter(messageEntity -> MessageEntityType.BOT_COMMAND
                            .equals(MessageEntityType.getMessageEntityTypeByMessageEntity(messageEntity)))
                    .anyMatch(messageEntity -> BotCommand.START
                            .equals(BotCommand.getBotCommandByMessageEntity(messageEntity)));
        }

        return Boolean.FALSE;
    }

    private void deleteUser(User user) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Удаление данных пользователя User: {}", user);
        }

        userService.delete(user);
    }
}
