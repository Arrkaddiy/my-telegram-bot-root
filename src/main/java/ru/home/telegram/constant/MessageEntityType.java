package ru.home.telegram.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.home.telegram.exception.BotRoutingException;

import java.util.Arrays;

@AllArgsConstructor
public enum MessageEntityType {

    BOLD("bold"),
    BOT_COMMAND("bot_command"),
    CASH_TAG("cashtag"),
    CODE("code"),
    EMAIL("email"),
    HASH_TAG("hashtag"),
    ITALIC("italic"),
    MENTION("mention"),
    PHONE_NUMBER("phone_number"),
    PRE("pre"),
    STRIKE_THROUGH("strikethrough"),
    TEXT_LINK("text_link"),
    TEXT_MENTION("text_mention"),
    UNDERLINE("underline"),
    URL("url");

    @Getter
    private String type;

    public static MessageEntityType getMessageEntityByType(String type) {
        return Arrays.stream(values())
                .filter(messageEntityType -> messageEntityType.getType().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(BotRoutingException::new);
    }
}
