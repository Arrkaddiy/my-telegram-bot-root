package ru.home.telegram.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.state.facade.StateFacadeImpl;
import ru.home.telegram.state.start.StartState;
import ru.home.telegram.state.start.StartStateImpl;

/**
 * StateContext.
 */
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
     * @param context Бин контекста
     * @return {@link StateFacadeImpl}
     */
    @Bean
    public StateFacade stateFacade(ApplicationContext context) {
        return new StateFacadeImpl(context);
    }
}
