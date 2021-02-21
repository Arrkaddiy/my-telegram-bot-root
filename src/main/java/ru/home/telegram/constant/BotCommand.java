package ru.home.telegram.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.util.Arrays;

@AllArgsConstructor
public enum BotCommand {
    START("/start"),
    BACK("/back"),
    HELP("/help");

    @Getter
    private String command;

    public static BotCommand getBotCommandByMessageEntity(MessageEntity messageEntity) {
        return Arrays.stream(values())
                .filter(botCommand -> botCommand.getCommand().equals(messageEntity.getText()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
