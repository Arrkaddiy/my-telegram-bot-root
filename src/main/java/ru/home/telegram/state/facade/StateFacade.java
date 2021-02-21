package ru.home.telegram.state.facade;

import ru.home.telegram.constant.BotStateType;
import ru.home.telegram.state.State;

public interface StateFacade {

    State route(BotStateType stateType);
}
