package ru.home.telegram.state.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRuntimeException;
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.start.StartState;
import ru.home.telegram.update.constant.UpdateContent;

@Slf4j
@RequiredArgsConstructor
public class StateFacadeImpl implements StateFacade {
    private static final String STATE_FACADE_ROUTE =
            "Маршрутизация состояния. BotStateType: {}, User: {}";
    private static final String STATE_FACADE_ROUTE_EXCEPTION =
            "Ошибка определения обработчика текущего состояния пользователя! BotStateType: ";

    private final ApplicationContext context;

    @Override
    public BotApiMethod<?> route(UpdateContent updateContent, User user, BotApiObject botApiObject) {
        BotStateType botStateType = user.getCurrentState();

        if (log.isDebugEnabled()) {
            log.debug(STATE_FACADE_ROUTE, botStateType, user);
        } else {
            log.info(STATE_FACADE_ROUTE, botStateType, user.getId());
        }

        switch (botStateType) {
            case START:
                return context.getBean(StartState.class).routeHandle(updateContent, user, botApiObject);
            default:
                throw new BotRuntimeException(STATE_FACADE_ROUTE_EXCEPTION + botStateType);
        }
    }
}
