package ru.home.telegram.state.facade;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.state.State;
import ru.home.telegram.state.authorizeemail.AuthorizeEmailState;
import ru.home.telegram.state.authorizephone.AuthorizePhoneState;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.start.StartState;

@Component
@RequiredArgsConstructor
@Qualifier(value = "stateFacade")
public class StateFacadeImp implements StateFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(StateFacadeImp.class);

    private final StartState startState;
    private final AuthorizePhoneState authorizePhoneState;
    private final AuthorizeEmailState authorizeEmailState;

    @Override
    public State route(BotStateType stateType) {
        LOGGER.info("");

        switch (stateType) {
            case START:
                return startState;
            case AUTHORIZE_PHONE:
                return authorizePhoneState;
            case AUTHORIZE_EMAIL:
                return authorizeEmailState;
            default:
                throw new BotRoutingException("Не найдена реализация обработки стадии: " + stateType);
        }
    }
}
