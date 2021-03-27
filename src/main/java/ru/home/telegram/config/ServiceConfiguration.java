package ru.home.telegram.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ServiceConfiguration {
    /**
     * UserName Телеграм Бота
     */
    @Value("${telegram.bot.userName}")
    private String botUsername;
    /**
     * Token Телеграм Бота
     */
    @Value("${telegram.bot.token}")
    private String botToken;
    /**
     * WebHook Телеграм Бота
     */
    @Value("${telegram.bot.webHook}")
    private String botPath;
}
