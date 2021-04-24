package ru.home.telegram.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.util.Arrays;

@RequiredArgsConstructor
public enum BotCommand {
    START("/start"),
    HELP("/help");

    @Getter
    private final String command;

    public static BotCommand getBotCommandByMessageEntity(MessageEntity messageEntity) {
        return Arrays.stream(values())
                .filter(botCommand -> botCommand.getCommand().equals(messageEntity.getText()))
                .findFirst()
                .orElse(null);
    }
}
