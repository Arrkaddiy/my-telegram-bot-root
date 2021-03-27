package ru.home.telegram.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.home.telegram.config.ServiceConfiguration;
import ru.home.telegram.db.repository.UserRepository;
import ru.home.telegram.service.UserService;
import ru.home.telegram.service.UserServiceImpl;

/**
 * CommonContext.
 */
@Configuration
public class CommonContext {
    /**
     * Бин конфигурации приложения
     *
     * @return {@link ServiceConfiguration}
     */
    @Bean
    public ServiceConfiguration serviceConfiguration() {
        return new ServiceConfiguration();
    }
    /**
     * Бин серивиса для Entity User {@link ru.home.telegram.db.entity.User}
     *
     * @param userRepository Репозиторий {@link UserRepository}
     * @return {@link UserServiceImpl}
     */
    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }
}
