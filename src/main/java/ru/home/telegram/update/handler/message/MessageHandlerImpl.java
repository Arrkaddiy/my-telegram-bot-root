package ru.home.telegram.update.handler.message;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.constant.BotCommand;
import ru.home.telegram.constant.MessageEntityType;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.handler.AbstractUpdateHandler;

@Slf4j
public class MessageHandlerImpl extends AbstractUpdateHandler implements MessageHandler {
    private static final String HANDLE_MESSAGE = "Обработка события Message, объект Message: {}";
    private static final String HANDLE_MESSAGE_RESTART = "Перезапуск клиента Telegram Bot'а для User {}";

    public MessageHandlerImpl(UserService userService, StateFacade stateFacade) {
        super(userService, stateFacade);
    }

    /**
     * Обработка входящего запроса типа Message
     *
     * @param message - Входящий запрос типа {@link Message}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> handle(Message message) {
        if (log.isDebugEnabled()) {
            log.debug(HANDLE_MESSAGE, message);
        } else {
            log.info(HANDLE_MESSAGE, message.getMessageId());
        }

        User user = getUser(message.getFrom());

        BotStateType currentState;
        if (isNeedRestart(message)) {
            log.info(HANDLE_MESSAGE_RESTART, user.getTelegramId());
            currentState = BotStateType.START;
        } else {
            currentState = user.getCurrentState();
        }

        return stateFacade.handleMessage(currentState, user, message);
    }

    private boolean isNeedRestart(Message message) {
        return Boolean.TRUE.equals(message.getEntities().stream()
                .filter(messageEntity -> MessageEntityType.BOT_COMMAND.getType().equals(messageEntity.getType()))
                .anyMatch(messageEntity -> BotCommand.RESTART.getCommand().equals(messageEntity.getText())));

    }
}
