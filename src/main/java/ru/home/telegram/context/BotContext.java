package ru.home.telegram.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.generics.WebhookBot;
import ru.home.telegram.bot.MyTelegramBot;
import ru.home.telegram.config.ServiceConfiguration;
import ru.home.telegram.db.repository.UserRepository;
import ru.home.telegram.service.UserService;
import ru.home.telegram.service.UserServiceImpl;
import ru.home.telegram.update.facade.UpdateFacade;

@Configuration
public class BotContext {

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

    /**
     * Бин телеграм бота
     *
     * @param updateFacade         Бин маршрутизации входящего запроса
     * @param serviceConfiguration Бин конфигурации приложения
     * @return {@link MyTelegramBot}
     */
    @Bean
    public WebhookBot myTelegramBot(UpdateFacade updateFacade, ServiceConfiguration serviceConfiguration) {
        return new MyTelegramBot(updateFacade, serviceConfiguration);
    }

}
