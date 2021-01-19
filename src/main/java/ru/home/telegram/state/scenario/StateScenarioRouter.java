package ru.home.telegram.state.scenario;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.constant.BotCommand;
import ru.home.telegram.constant.BotStateType;
import ru.home.telegram.constant.MessageEntityType;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.exception.BotRuntimeException;

@Component
public class StateScenarioRouter {
    @Setter(onMethod_ = {@Autowired})
    private StateScenario startScenario;
    @Setter(onMethod_ = {@Autowired})
    private StateScenario authorizePhoneScenario;
    @Setter(onMethod_ = {@Autowired})
    private StateScenario authorizeEmailScenario;

    public BotApiMethod<?> route(BotStateType stateType, User user, Message message) {
        StateScenario stateScenario = getStateScenario(stateType);
        BotCommand botCommand = getBotCommand(message);
        return stateScenario.getScenario().getScenarioMap().get(botCommand).apply(user, message);
    }

    private StateScenario getStateScenario(BotStateType stateType) {
        switch (stateType) {
            case START:
                return startScenario;
            case AUTHORIZATION_PHONE:
                return authorizePhoneScenario;
            case AUTHORIZATION_EMAIL:
                return authorizeEmailScenario;
            default:
                throw new BotRoutingException();
        }
    }

    private BotCommand getBotCommand(Message message) {

        if (message.hasEntities()) {
            return BotCommand.getBotCommandByMessageEntity(
                    message.getEntities().stream()
                            .filter(messageEntity -> MessageEntityType.BOT_COMMAND
                                    .equals(MessageEntityType.getMessageEntityTypeByMessageEntity(messageEntity)))
                            .findFirst()
                            .orElseThrow(BotRuntimeException::new));
        }

        return BotCommand.NONE;
    }
}
