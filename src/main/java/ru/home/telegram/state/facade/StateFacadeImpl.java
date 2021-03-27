package ru.home.telegram.state.facade;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.home.telegram.state.State;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.start.StartState;

@RequiredArgsConstructor
public class StateFacadeImpl implements StateFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(StateFacadeImpl.class);
    private static final String STATE_FACADE_ROUTE = "Маршрутизация состояния StateType: {}";

    private final StartState startState;

    @Override
    public State route(BotStateType stateType) {
        LOGGER.info(STATE_FACADE_ROUTE, stateType);

        switch (stateType) {
            case START:
                return startState;
            default:
                return null;
        }
    }
}
