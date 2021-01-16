package ru.home.telegram.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.home.telegram.exception.BotRoutingException;

import java.util.Arrays;

@AllArgsConstructor
public enum BotCommand {
    START("/start"),
    BACK("/back"),
    HELP("/help");

    @Getter
    private String command;

    public static BotCommand getCommandByText(String text) {
        return Arrays.stream(values())
                .filter(botCommand -> botCommand.getCommand().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(BotRoutingException::new);
    }
}
