package ru.home.telegram.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.state.facade.StateFacadeImpl;
import ru.home.telegram.state.start.StartState;
import ru.home.telegram.state.start.StartStateImpl;

@Configuration
public class StateContext {

    /**
     * Бин состояния START
     *
     * @return {@link StartStateImpl}
     */
    @Bean
    public StartState startState() {
        return new StartStateImpl();
    }

    /**
     * Бин маршрутизации состояния пользователя
     *
     * @param startState Бин состояния START
     * @return {@link StateFacadeImpl}
     */
    @Bean
    public StateFacade stateFacade(StartState startState) {
        return new StateFacadeImpl(startState);
    }
}
