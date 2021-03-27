package ru.home.telegram.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.generics.WebhookBot;
import ru.home.telegram.bot.MyTelegramBot;
import ru.home.telegram.config.ServiceConfiguration;
import ru.home.telegram.update.facade.UpdateFacade;

@Configuration
public class BotContext {

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
