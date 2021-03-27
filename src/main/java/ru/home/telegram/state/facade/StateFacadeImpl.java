package ru.home.telegram.state.facade;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.state.State;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.start.StartState;

@RequiredArgsConstructor
public class StateFacadeImpl implements StateFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(StateFacadeImpl.class);

    private final StartState startState;

    @Override
    public State route(BotStateType stateType) {
        LOGGER.info("Маршрутизация состояния StateType: {}", stateType);

        switch (stateType) {
            case START:
                return startState;
            default:
                throw new BotRoutingException("Не найдена реализация обработки стадии: " + stateType);
        }
    }
}
